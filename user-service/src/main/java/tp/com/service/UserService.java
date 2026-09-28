package tp.com.service;

import tp.com.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    Optional<UserDto> getUserById(Long id);
    Optional<UserDto> updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
}
