package org.toDo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.toDo.entity.Task;
import org.toDo.entity.TaskRequest;
import org.toDo.entity.TaskStatus;
import org.toDo.services.TaskService;
import org.toDo.services.UserService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        logger.info("Received task creation request with title: {}", taskRequest.getTitle());
        return taskService.create(taskRequest.getTitle(), taskRequest.getDescription(), taskRequest.getDueDate(), taskRequest.getUsername());
    }
    @GetMapping("/all")
    public List<Task> getAllTasks(@RequestParam String username) {
        return taskService.findTasksByUserName(username);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }
    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam String username, @RequestParam TaskStatus status) {
        return taskService.findTasksByUserNameAndStatus(username, status);
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String username, @RequestParam String keyword) {
        return taskService.findTasksByUserNameAndKeyword(username, keyword);
    }

    @GetMapping("/duedate")
    public List<Task> getTasksByDueDate(@RequestParam String username, @RequestParam LocalDate dueDate) {
        return taskService.findTasksByUserNameAndDueDate(username, dueDate);
    }
//    @PostMapping("/categorize/{id}")
//    public Task categorizeTask(@PathVariable Long id, @RequestParam String category) {
//        return taskService.categorizeTask(id, category);
//    }
}

