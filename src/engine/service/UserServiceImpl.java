package engine.service;

import engine.model.User;
import engine.model.dto.UserDto;
import engine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Object> addUser(UserDto user) {
        User newUser = new User(user.getEmail(), user.getPassword());
        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
