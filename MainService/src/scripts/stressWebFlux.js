import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';

export const options = {
  scenarios: {
      constant_rps: {
        executor: 'constant-arrival-rate',
        rate: 900,
        timeUnit: '1s',
        duration: '1m',
        preAllocatedVUs: 200,
        maxVUs: 200,
        exec: 'sendRequests',
      },
    },
    thresholds: {
      http_req_failed: ['rate<0.01'],
      http_req_duration: ['p(95)<3000'],
    },
};

// собственные метрики
const httpReqDuration = new Trend('http_req_duration_ms');
const httpReqRate = new Rate('http_req_rate');
const httpReqErrors = new Counter('http_req_errors');

export function sendRequests() {
  const payload = JSON.stringify({
    message: 'aokihary'
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post('http://localhost:8087', payload, params);

  const success = check(res, {
    'status is 200': (r) => r.status === 200,
  });

  if (!success) {
    httpReqErrors.add(1);
  }

  httpReqDuration.add(res.timings.duration);
  httpReqRate.add(1);
}