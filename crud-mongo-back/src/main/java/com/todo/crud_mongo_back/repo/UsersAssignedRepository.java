package com.todo.crud_mongo_back.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.todo.crud_mongo_back.model.UsersAssigned;

@Repository
public interface UsersAssignedRepository extends MongoRepository <UsersAssigned, String> {

}
