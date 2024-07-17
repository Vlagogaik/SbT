package org.toDo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toDo.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAll();

    User findByUsername(String username);
}
