package tp.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.com.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
