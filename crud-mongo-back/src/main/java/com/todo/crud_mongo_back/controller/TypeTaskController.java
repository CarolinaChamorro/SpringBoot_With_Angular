package com.todo.crud_mongo_back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.crud_mongo_back.model.TypeTask;
import com.todo.crud_mongo_back.repo.TypeTaskRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/typeTasks")
public class TypeTaskController {

    @Autowired
    private TypeTaskRepository typeTaskRepository;

    @GetMapping("")
    List<TypeTask> index(){
        return typeTaskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    TypeTask create(@RequestBody TypeTask typeTask){
        return typeTaskRepository.save(typeTask);
    }

    @PutMapping("/{id}")
    TypeTask update(@PathVariable String id, @RequestBody TypeTask typeTask){
        TypeTask typeTaskFromDb = typeTaskRepository.findById(id)
                                .orElseThrow(RuntimeException::new);

        typeTaskFromDb.setType_name(typeTask.getType_name());
        typeTaskFromDb.setStatus(typeTask.isStatus());

        return typeTaskRepository.save(typeTaskFromDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id){
        TypeTask typeTask = typeTaskRepository.findById(id)
                            .orElseThrow(()-> new RuntimeException("Type not found for this id:: "+ id));
        
        typeTaskRepository.delete(typeTask);
        Map<String, String> response = new HashMap<>();
        response.put("deleted", "true");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }
    

}
