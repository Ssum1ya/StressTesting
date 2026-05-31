import http from 'k6/http';
import { check, sleep } from 'k6';
//import { Trend, Rate, Counter } from 'k6/metrics';

const VARIANT = __ENV.VARIANT;

export const options = {
  tags: { variant: VARIANT },
  scenarios: {
    ramping_rps: {
      executor: 'ramping-arrival-rate',
      startRate: 50,
      timeUnit: '1s',
      preAllocatedVUs: 500,
      maxVUs: 500,
      exec: 'sendRequests',
      stages: [
              { target: 100,  duration: '15s' },
              { target: 200, duration: '15s' },
              { target: 500, duration: '10s' },
              { target: 500, duration: '10s' },
            ],
    },
  },
  thresholds: {
    http_req_failed: ['rate<0.5'],
    http_req_duration: ['p(95)<10000'],
  },
};

export function sendRequests() {
  const res = http.post('http://localhost/', JSON.stringify({ message: 'test' }), {
      headers: { 'Content-Type': 'application/json',
                  'Host': 'benchmark.local'},
      tags: { variant: 'kubernetes-app' },
    });

  console.log(res);
  const success = check(res, {
    'status is 200': (r) => r.status === 200,
  });
}