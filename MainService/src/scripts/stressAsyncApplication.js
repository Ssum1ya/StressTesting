import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Gauge, Counter, Rate } from 'k6/metrics';
import exec from 'k6/execution';

export const options = {
  vus: 150,
  duration: '30s',
};

const minLatency = new Trend('http_min_latency_ms', true);

export default function () {

  const payload = JSON.stringify({
    message: 'aokihary'
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const start = Date.now();
  const response = http.post('http://localhost:8083/', payload, params);
  const duration = Date.now() - start;
  minLatency.add(duration);

  sleep(0.1);
}