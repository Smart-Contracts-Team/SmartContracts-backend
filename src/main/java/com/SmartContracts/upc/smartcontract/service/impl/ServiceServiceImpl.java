package com.SmartContracts.upc.smartcontract.service.impl;

import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.smartcontract.model.ServiceU;
import com.SmartContracts.upc.smartcontract.model.Task;
import com.SmartContracts.upc.smartcontract.repository.ServiceRepository;
import com.SmartContracts.upc.smartcontract.repository.TaskRepository;
import com.SmartContracts.upc.smartcontract.service.ServiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final TaskRepository taskRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository, TaskRepository taskRepository){
        this.serviceRepository = serviceRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<ServiceU> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public List<ServiceU> getServiceByUserId(Long id) {
        return serviceRepository.findByUserId(id);
    }

    @Override
    public List<ServiceU> getServiceByCategory(String category) {
        return serviceRepository.findByCategory(category);
    }

    @Override
    public ServiceU getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceU createService(ServiceU service) {

        if (service.getTasks() != null) {
            // Guarda cada tarea y la asocia al servicio
            for (Task task : service.getTasks()) {
                task.setServiceU(service);
                taskRepository.save(task);
            }
        }
        return serviceRepository.save(service);
    }

    @Override
    public ServiceU updateService(ServiceU service) {
        ServiceU serviceToUpdate = getServiceById(service.getId());
        if (serviceToUpdate != null) {
            // Actualizar campos del servicio
            serviceToUpdate.setCategory(service.getCategory());
            serviceToUpdate.setName(service.getName());
            serviceToUpdate.setDescription(service.getDescription());
            serviceToUpdate.setPrice(service.getPrice());
            serviceToUpdate.setStarts(service.getStarts());
            serviceToUpdate.setPhoto(service.getPhoto());
            serviceToUpdate.setState(service.getState());
            serviceToUpdate.setUser(service.getUser());

            // Manejo de tareas
            List<Task> updatedTasks = service.getTasks();
            List<Task> existingTasks = serviceToUpdate.getTasks();

            // Actualizar o añadir nuevas tareas
            for (Task updatedTask : updatedTasks) {
                if (updatedTask.getId() != null) {
                    // Tarea existente, actualiza sus valores
                    Task existingTask = existingTasks.stream()
                            .filter(t -> t.getId().equals(updatedTask.getId()))
                            .findFirst()
                            .orElseThrow(() -> new ValidationException("Task not found with id " + updatedTask.getId()));
                    existingTask.setTaskName(updatedTask.getTaskName());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setStatus(updatedTask.getStatus());
                } else {
                    // Nueva tarea, asóciala y guárdala
                    updatedTask.setServiceU(serviceToUpdate);
                    taskRepository.save(updatedTask);
                    existingTasks.add(updatedTask);
                }
            }

            // Eliminar tareas que no están en la solicitud
            List<Task> tasksToRemove = existingTasks.stream()
                    .filter(task -> updatedTasks.stream().noneMatch(t -> t.getId() != null && t.getId().equals(task.getId())))
                    .collect(Collectors.toList());

            for (Task taskToRemove : tasksToRemove) {
                taskRepository.delete(taskToRemove);
                existingTasks.remove(taskToRemove);
            }

            return serviceRepository.save(serviceToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteService(Long id) {
        ServiceU service = getServiceById(id);
        if (service != null) {
            // Eliminar todas las tareas asociadas al servicio
            for (Task task : service.getTasks()) {
                taskRepository.delete(task);
            }
            // Luego, elimina el servicio
            serviceRepository.deleteById(id);
        } else {
            throw new ValidationException("Service not found with id " + id);
        }
    }

    @Override
    public void validateService(ServiceU service) {
        if(service==null){
            throw new ValidationException("El servicio no puede ser nulo");
        }
        if(service.getName() == null || service.getName().isEmpty()){
            throw new ValidationException("El nombre del servicio es necesario");
        }
        if(service.getName().length() >150){
            throw new ValidationException("El nombre del servicio es demasiado largo");
        }
        if(service.getDescription() == null || service.getDescription().isEmpty()){
            throw new ValidationException("La descripción del servicio es necesaria");
        }
        if(service.getDescription().length() >300){
            throw new ValidationException("La descripción es demasiado larga");
        }
        if(service.getCategory() == null || service.getCategory().isEmpty()){
            throw new ValidationException("La categoría es obligatoria");
        }
        if(service.getPrice() == null || service.getPrice() ==0 ){
            throw new ValidationException("El precio del servicio es obligatorio");
        }
        if(service.getStarts() <= 0 || service.getStarts() >5){
            throw new ValidationException("Las estrellas para el servicio son obligatorias");
        }
        if(service.getPhoto() == null || service.getPhoto().isEmpty()){
            throw new ValidationException("Es necesario añadir una foto para el servicio");
        }
        if(service.getState() == null || service.getState().isEmpty()){
            throw new ValidationException("Es necesario el estado del servicio");
        }
    }
}
