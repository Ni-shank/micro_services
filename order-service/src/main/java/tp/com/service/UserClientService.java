package tp.com.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import tp.com.client.UserClient;
import tp.com.dto.UserDto;

@Service
public class UserClientService {

    private final UserClient userClient;

    public UserClientService(UserClient userClient) {
        this.userClient = userClient;
    }

    /*
     * Every Feign call now passes through:
     * RateLimiter -> Retry/CircuitBreaker protection -> Feign -> User Service
     */
    @RateLimiter(name = "userService")
  @Retry(name = "userService")
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserByIdFallback")
    public UserDto getUserById(Long userId) {
        return userClient.getUserById(userId);
    }

    public UserDto getUserByIdFallback(Long userId, Throwable throwable) {
        System.out.println("User service unavailable. Fallback executed for userId=" + userId
                + ", reason=" + throwable.getClass().getSimpleName());

        return null;
    }
}
