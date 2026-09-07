package com.opscore.service;

import com.opscore.dto.TaskDTO;
import com.opscore.exception.TaskNotFoundException;
import com.opscore.model.Task;
import com.opscore.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long taskID){
        return taskRepository.findById(taskID)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public Task createTask(TaskDTO taskDTO) {

        Task task = new Task();

        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskID) {
      if (!taskRepository.existsById(taskID)) {
          throw new TaskNotFoundException("task not found");
      }
        taskRepository.deleteById(taskID);
    }



    public Task updateTask(Long taskID, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskID)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));

        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());

        return taskRepository.save(task);
    }


}