package com.todo.crud_mongo_back.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document (collection = "usersAssigned")
public class UsersAssigned {
    @Id
    private String id;
    private String name;
    private String last_name;
    private String username;
    private boolean status;

}
