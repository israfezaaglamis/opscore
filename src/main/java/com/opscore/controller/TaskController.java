package com.opscore.controller;

import com.opscore.dto.TaskDTO;
import com.opscore.model.Task;
import jakarta.validation.Valid;
import com.opscore.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
   }

   @GetMapping
    public ResponseEntity<List<Task>>getTasks(){
        return ResponseEntity.ok(taskService.findAll());
   }

   @GetMapping("/{taskID}")
   public ResponseEntity<Task>getTaskByID(@PathVariable Long taskID){
        return ResponseEntity.ok(taskService.findById(taskID));
   }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskID}")
    public ResponseEntity<Task>deleteTask(@PathVariable Long taskID){
        taskService.deleteTask(taskID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskID}")
    public ResponseEntity<Task>updateTask
            (@PathVariable("taskID") Long taskID,
             @Valid @RequestBody TaskDTO taskDTO){

        Task updatedTask = taskService.updateTask(taskID,taskDTO);
        return ResponseEntity.ok(updatedTask);

    }



}
