package com.SmartContracts.upc.smartcontract.controller;

import com.SmartContracts.upc.smartcontract.model.Task;
import com.SmartContracts.upc.smartcontract.model.TaskDto;
import com.SmartContracts.upc.smartcontract.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/smartcontract/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/task
    // Method: GET

    @Transactional
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        if(taskService.getAllTasks().isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/task/{taskId}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name="taskId")Long taskId){
        if(taskService.getTaskById(taskId) == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(taskService.getTaskById(taskId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/task/service/{serviceId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Task>> getTasksByServiceId(@PathVariable(name = "serviceId")Long serviceId){
        if(taskService.getAllTasksByService(serviceId) == null){
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Task>>(taskService.getAllTasksByService(serviceId),HttpStatus.OK);
    }


    // URL: http://localhost:8080/api/smartcontract/v1/task
    // Method: POST

    @Transactional
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        taskService.validateTask(task);

        return new ResponseEntity<Task>(taskService.createTask(task),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/task/{taskId}
    // Method: PUT
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable(name = "taskId")Long taskId, @RequestBody TaskDto taskRequest){

        Task taskToUpdate = taskService.getTaskById(taskId);
        if(taskToUpdate == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        taskToUpdate.setTaskName(taskRequest.getTaskName());
        taskToUpdate.setDescription(taskRequest.getDescription());
        taskToUpdate.setStatus(taskRequest.getStatus());
        taskToUpdate.setDueDate(taskRequest.getDueDate());

        taskService.validateTask(taskToUpdate);
        return new ResponseEntity<Task>(taskService.updateTask(taskToUpdate),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service/{serviceId}
    // Method: DELETE
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteService(@PathVariable(name = "taskId")Long taskId){
        if(taskService.getTaskById(taskId) == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(taskId);
        return new ResponseEntity<String>("Task deleted successfully",HttpStatus.OK);
    }
}
