package org.toDo.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String username;
    private TaskStatus STATUS;
}
