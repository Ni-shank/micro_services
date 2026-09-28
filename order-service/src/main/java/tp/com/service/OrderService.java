package tp.com.service;

import tp.com.dto.OrderDto;
import tp.com.dto.OrderWithUserDto;
import tp.com.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    Optional<OrderDto> getOrderById(Long id);
    Optional<OrderDto> updateOrder(Long id, OrderDto orderDto);
    boolean deleteOrder(Long id);
    Optional<UserDto> getUserForOrder(Long orderId);
    Optional<OrderWithUserDto> getOrderWithUser(Long id);
}
