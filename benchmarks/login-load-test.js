import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 10,
  duration: '30s',
};

export default function () {
  const payload = JSON.stringify({
    email: 'ananya@gmail.com',
    password: 'Password123',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(
    'http://localhost:8080/login',
    payload,
    params
  );

  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}