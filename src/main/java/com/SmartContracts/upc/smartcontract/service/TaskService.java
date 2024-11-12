package com.SmartContracts.upc.smartcontract.service;

import com.SmartContracts.upc.smartcontract.model.Task;

import java.util.List;

public interface TaskService {

    public abstract Task createTask(Task task);
    public abstract Task getTaskById(Long id);
    public abstract Task updateTask(Task task);
    public abstract void deleteTask(Long id);
    public abstract List<Task> getAllTasks();
    public abstract List<Task> getAllTasksByService(Long serviceId);

    public void existsTaskById(Long id);
    public void validateTask(Task task);
}
