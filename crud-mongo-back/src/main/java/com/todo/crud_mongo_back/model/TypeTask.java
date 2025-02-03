package com.todo.crud_mongo_back.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document (collection = "typeTasks")
public class TypeTask {
    @Id
    private String id;
    private String type_name;
    private boolean status;
}
