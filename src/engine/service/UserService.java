package engine.service;

import engine.model.User;

import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Optional<User> findUserByEmail(String email);
}
