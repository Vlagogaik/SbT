package org.toDo.services;

import org.toDo.entity.Task;
import org.toDo.entity.TaskRequest;
import org.toDo.entity.TaskStatus;
import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    Task create(String title, String description, LocalDate dueDate, String username);

    List<Task> findTasksByUserName(String username);

    List<Task> findTasksByUserNameAndKeyword(String username, String keyword);

    List<Task> findTasksByUserNameAndStatus(String username, TaskStatus status);

    List<Task> findTasksByUserNameAndDueDate(String username, LocalDate dueDate);

    Task update(Long id, TaskRequest taskRequest);

    void delete(Long taskId);

    List<Task> findAll();

//    Task categorizeTask(Long taskId, String category);
}