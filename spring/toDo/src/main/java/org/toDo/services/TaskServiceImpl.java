package org.toDo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.toDo.entity.Task;
import org.toDo.entity.TaskRequest;
import org.toDo.entity.TaskStatus;
import org.toDo.entity.User;
import org.toDo.repositories.TaskRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Override
    public Task create(String title, String description, LocalDate dueDate, Long userId) {
        Optional<User> user = userService.findById(userId);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(TaskStatus.OPEN);
        task.setUser(user.get());
        logger.info("Created task with id: {}", task.getId());
        return taskRepository.save(task);
    }
    @Override
    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Override
    public List<Task> getAllTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> getTasksByUserIdAndStatus(Long userId, TaskStatus status) {
        return taskRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public Task update(Long id, TaskRequest taskRequest) {
        Optional<Task> task0 = taskRepository.findById(id);
        Task task = task0.get();
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setDueDate(task.getDueDate());
        task.setStatus(task.getStatus());
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
