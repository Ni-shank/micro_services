package tp.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.com.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
