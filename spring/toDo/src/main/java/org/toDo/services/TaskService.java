package org.toDo.services;

import org.toDo.entity.Task;
import org.toDo.entity.TaskRequest;
import org.toDo.entity.TaskStatus;
import org.toDo.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    Task create(String title, String description, LocalDate dueDate, Long userId);

    List<Task> findTasksByUser(User user);

    List<Task> getAllTasksByUserId(Long userId);

    List<Task> getTasksByUserIdAndStatus(Long userId, TaskStatus status);

    Task update(Long id, TaskRequest taskRequest);

    void delete(Long taskId);

    List<Task> findAll();
}