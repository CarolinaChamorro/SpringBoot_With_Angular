package com.todo.crud_mongo_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

import com.todo.crud_mongo_back.repo.TaskRepository;
import com.todo.crud_mongo_back.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;	

    @GetMapping("")
    List<Task> index() {
        return taskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    Task create(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    Task update(@PathVariable String id, @RequestBody Task task){
        Task taskFromDb = taskRepository.findById(id)
                        .orElseThrow(RuntimeException::new);

        taskFromDb.setName(task.getName());
        taskFromDb.setStart_date(task.getStart_date());
        taskFromDb.setEnd_date(task.getEnd_date());
        taskFromDb.setType_task(task.getType_task());
        taskFromDb.setUser_assigned(task.getUser_assigned());
        taskFromDb.setCompleted(task.isCompleted());

        return taskRepository.save(taskFromDb);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id){
        Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));

        taskRepository.delete(task);
        Map<String, String> response = new HashMap<>();
        response.put("deleted", "true");
        response.put("id", id);
        return ResponseEntity.ok(response);

    }

}
