package com.todo.crud_mongo_back.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String start_date;
    private String end_date;
    private String type_task;
    private String user_assigned;
    private boolean completed;
}
