package org.toDo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toDo.entity.Task;
import org.toDo.entity.TaskStatus;
import org.toDo.entity.User;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);

    List<Task> findByUser(User user);
}