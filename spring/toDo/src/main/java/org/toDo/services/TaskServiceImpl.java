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
    public Task create(String title, String description, LocalDate dueDate, String username) {
        User user = userService.findByUserName(username);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(TaskStatus.OPEN);
        task.setUser(user);
        return taskRepository.save(task);
    }
    @Override
    public List<Task> findTasksByUserName(String username) {
        User user = userService.findByUserName(username);
        return taskRepository.findByUser(user);
    }


    @Override
    public List<Task> findTasksByUserNameAndStatus(String username, TaskStatus status) {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return taskRepository.findByUserIdAndStatus(user.getId(), status);
    }

    @Override
    public List<Task> findTasksByUserNameAndDueDate(String username, LocalDate dueDate) {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return taskRepository.findByUserAndDueDate(user, dueDate);
    }

//    @Override
//    public Task categorizeTask(Long taskId, String category) {
//        Optional<Task> taskOptional = taskRepository.findById(taskId);
//        Task task = taskOptional.orElseThrow(() -> new RuntimeException("Task not found"));
//        task.setCategory(category);
//        return taskRepository.save(task);
//    }

    @Override
    public List<Task> findTasksByUserNameAndKeyword(String username, String keyword) {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return taskRepository.findByUserAndTitleContainingOrDescriptionContaining(user, keyword, keyword);
    }

    @Override
    public Task update(Long id, TaskRequest taskRequest) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setDueDate(taskRequest.getDueDate());
            task.setStatus(taskRequest.getSTATUS());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found");
        }
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
