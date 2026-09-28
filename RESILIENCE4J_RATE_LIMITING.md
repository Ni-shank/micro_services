# Resilience4j + Rate Limiting

Implemented in `order-service`.

## Protected Feign call

`GET /orders/{id}/user` and `GET /orders/{id}/userwithorder` call `UserClientService#getUserById()`.

The Feign call is protected with:

- `@RateLimiter(name = "userService")`
- `@Retry(name = "userService")`
- `@CircuitBreaker(name = "userService", fallbackMethod = "getUserByIdFallback")`

## Configuration

- 5 calls per 10 seconds
- Rate limiter timeout: 0 seconds (reject immediately)
- Circuit breaker window: 5 calls
- Circuit breaker failure threshold: 50%
- Circuit breaker open state: 10 seconds
- Half-open permitted calls: 3
- Retry attempts: 3

## Rate-limit response

After the first 5 protected calls within the same 10-second period, the next call returns HTTP 429:

```json
{
  "timestamp": "...",
  "status": 429,
  "error": "Too Many Requests",
  "message": "Rate limit exceeded. Please try again later."
}
```

## Test

Start:

1. service-registry
2. user-service
3. order-service
4. api-gateway

Then call repeatedly:

`GET http://localhost:8080/orders/1/user`

Repeat more than 5 times within 10 seconds to see rate limiting.

To test Circuit Breaker, stop `user-service` and repeatedly call the same endpoint. Feign failures are retried; after the configured failure threshold the circuit opens and the fallback is used.

Actuator:

- `/actuator/health`
- `/actuator/metrics`
