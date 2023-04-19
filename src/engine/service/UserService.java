package engine.service;

import engine.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> addUser(UserDto user);
}
