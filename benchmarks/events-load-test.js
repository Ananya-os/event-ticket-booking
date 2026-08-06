import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 100,
  duration: '30s',
};

const token = __ENV.JWT;

export default function () {

  const res = http.get('http://localhost:8080/events', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}