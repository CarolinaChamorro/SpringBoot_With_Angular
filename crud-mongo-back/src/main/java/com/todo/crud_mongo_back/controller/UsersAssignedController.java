package com.todo.crud_mongo_back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.crud_mongo_back.model.UsersAssigned;
import com.todo.crud_mongo_back.repo.UsersAssignedRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/usersAssigned")
public class UsersAssignedController {
    
    @Autowired
    private UsersAssignedRepository usersAssignedRepository;

    @GetMapping("")
    List<UsersAssigned> index (){
        return usersAssignedRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    UsersAssigned create(@RequestBody UsersAssigned usersAssigned){
        return usersAssignedRepository.save(usersAssigned);
    }

    @PutMapping("/{id}")
    UsersAssigned update(@PathVariable String id, @RequestBody UsersAssigned usersAssigned){
        UsersAssigned usersAssignedFromDb = usersAssignedRepository.findById(id)
                                            .orElseThrow(RuntimeException::new);
        
        usersAssignedFromDb.setName(usersAssigned.getName());
        usersAssignedFromDb.setLast_name(usersAssigned.getLast_name());
        usersAssignedFromDb.setUsername(usersAssigned.getUsername());
        usersAssignedFromDb.setStatus(usersAssigned.isStatus());

        return usersAssignedRepository.save(usersAssignedFromDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id){
        UsersAssigned users = usersAssignedRepository.findById(id)
                                .orElseThrow(()-> new RuntimeException("User not found for this id :: "+ id));
        
        usersAssignedRepository.delete(users);
        Map<String, String> response = new HashMap<>();
        response.put("deleted", "true");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }


}
