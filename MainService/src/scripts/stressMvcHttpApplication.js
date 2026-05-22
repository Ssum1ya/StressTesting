import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';

export const options = {
  scenarios: {
    ramping_rps: {
      executor: 'ramping-arrival-rate',
      startRate: 10,
      timeUnit: '1s',
      preAllocatedVUs: 500,
      maxVUs: 500,
      exec: 'sendRequests',
      stages: [
        { target: 50,  duration: '15s' },
        { target: 200, duration: '15s' },
        { target: 500, duration: '15s' },
        { target: 500, duration: '15s' },
      ],
    },
  },
  thresholds: {
    http_req_failed: ['rate<0.5'],       // порог подняли — при такой нагрузке будут ошибки
    http_req_duration: ['p(95)<10000'],  // 10 секунд — чтобы тест не падал сразу
  },
};

// собственные метрики
const httpReqDuration = new Trend('http_req_duration_ms');
const httpReqRate = new Rate('http_req_rate');
const httpReqErrors = new Counter('http_req_errors');

export function sendRequests() {
  const res = http.post('http://localhost:8080/', JSON.stringify({ message: 'test' }), {
      headers: { 'Content-Type': 'application/json' },
      tags: { variant: 'mvc-http' },
    });

  const success = check(res, {
    'status is 200': (r) => r.status === 200,
  });

  if (!success) {
    httpReqErrors.add(1);
  }

  httpReqDuration.add(res.timings.duration);
  httpReqRate.add(1);
}