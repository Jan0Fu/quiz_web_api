package engine.service;

import engine.model.User;
import engine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        Optional<User> theUser = userRepository.findUserByEmail(user.getEmail());
        if (theUser.isEmpty()) {
            User newUser = User.builder().email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role("ROLE_USER")
                    .build();
            userRepository.save(newUser);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
