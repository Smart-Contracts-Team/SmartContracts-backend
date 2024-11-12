package com.SmartContracts.upc.smartcontract.service.impl;

import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.smartcontract.model.Task;
import com.SmartContracts.upc.smartcontract.repository.TaskRepository;
import com.SmartContracts.upc.smartcontract.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(Task task) {

        Task existingTask = getTaskById(task.getId());
        if(existingTask != null){
            existingTask.setTaskName(task.getTaskName());
            existingTask.setStatus(task.getStatus());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setServiceU(task.getServiceU());

            return taskRepository.save(existingTask);
        }

        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksByService(Long serviceId) {
        return taskRepository.findByServiceU_Id(serviceId);
    }

    @Override
    public void existsTaskById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ValidationException("No existe ninguna tarea");
        }
    }

    @Override
    public void validateTask(Task task) {
        if(task == null){
            throw new ValidationException("La tarea es nula");
        }
        if(task.getTaskName() == null || task.getTaskName().isEmpty()){
            throw new ValidationException("El nombre tiene que ser obligatorio");
        }
        if(task.getTaskName().length() > 100){
            throw new ValidationException("El nombre de la tarea es demasiado larga");
        }
        if(task.getDescription() == null || task.getDescription().isEmpty()){
            throw new ValidationException("La descripción tiene que ser obligatoria");
        }
        if(task.getStatus() == null || task.getStatus().isEmpty()){
            throw new ValidationException("El estado tiene que ser obligatorio");
        }
        if(task.getDueDate() == null){
            throw new ValidationException("La fecha de expiración debe ser obligatoria");
        }
    }
}
