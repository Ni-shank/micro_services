package tp.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.com.dto.OrderDto;
import tp.com.dto.OrderWithUserDto;
import tp.com.dto.UserDto;
import tp.com.entity.Order;
import tp.com.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClientService userClientService;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getUserId(), orderDto.getProduct(), orderDto.getAmount());
        Order saved = orderRepository.save(order);
        return toDto(saved);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<OrderDto> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toDto);
    }

    @Override
    public Optional<OrderDto> updateOrder(Long id, OrderDto orderDto) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setUserId(orderDto.getUserId());
                    existing.setProduct(orderDto.getProduct());
                    existing.setAmount(orderDto.getAmount());
                    return toDto(orderRepository.save(existing));
                });
    }

    @Override
    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<UserDto> getUserForOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> userClientService.getUserById(order.getUserId()));
    }

    private OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getUserId(), order.getProduct(), order.getAmount());
    }


    @Override
    public Optional<OrderWithUserDto> getOrderWithUser(Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    UserDto user = userClientService.getUserById(order.getUserId());
                    return new OrderWithUserDto(order.getId(), order.getProduct(), order.getAmount(), user);
                });
    }
}
