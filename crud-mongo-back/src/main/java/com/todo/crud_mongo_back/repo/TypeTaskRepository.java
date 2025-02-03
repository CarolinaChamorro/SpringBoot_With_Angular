package com.todo.crud_mongo_back.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.todo.crud_mongo_back.model.TypeTask;

@Repository
public interface TypeTaskRepository extends MongoRepository<TypeTask, String> {

}
