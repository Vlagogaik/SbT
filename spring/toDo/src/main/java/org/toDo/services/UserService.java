package org.toDo.services;

import org.toDo.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(String email, String password, String username);

    User findByEmail(String email);

    User findByUserName(String username);

    Optional<User> findById(Long userId);

    User login(String username, String password);

    List<User> findAllUsers();

}