package com.SmartContracts.upc.smartcontract.controller;

import com.SmartContracts.upc.smartcontract.model.ServiceU;
import com.SmartContracts.upc.smartcontract.model.Task;
import com.SmartContracts.upc.smartcontract.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/smartcontract/v1/service")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService){
        this.serviceService = serviceService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service
    // Method: GET

    @Transactional
    @GetMapping
    public ResponseEntity<List<ServiceU>> getAllServices(){
        if(serviceService.getAllServices().isEmpty()){
            return new ResponseEntity<List<ServiceU>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ServiceU>>(serviceService.getAllServices(),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service/{serviceId}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceU> getServiceById(@PathVariable(name="serviceId")Long serviceId){
        if(serviceService.getServiceById(serviceId)==null){
            return new ResponseEntity<ServiceU>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ServiceU>(serviceService.getServiceById(serviceId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service/category/{category}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ServiceU>> getServiceByCategory(@PathVariable(name="category")String category){
        if(serviceService.getServiceByCategory(category)==null){
            return new ResponseEntity<List<ServiceU>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ServiceU>>(serviceService.getServiceByCategory(category),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service/task/{taskId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Task>> getTaskSByServiceId(@PathVariable(name="taskId")Long taskId){
        if(serviceService.getTasksByServiceId(taskId)==null){
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Task>>(serviceService.getTasksByServiceId(taskId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service/user/{userId}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("user/{userId}")
    public ResponseEntity<List<ServiceU>> getServiceByUserId(@PathVariable(name="userId")Long userId){
        if(serviceService.getServiceByUserId(userId)==null){
            return new ResponseEntity<List<ServiceU>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ServiceU>>(serviceService.getServiceByUserId(userId),HttpStatus.OK);
    }


    // URL: http://localhost:8080/api/smartcontract/v1/service
    // Method: POST
    @Transactional
    @PostMapping
    public ResponseEntity<ServiceU> createService(@RequestBody ServiceU service){
        serviceService.validateService(service);

        return new ResponseEntity<ServiceU>(serviceService.createService(service),HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/service/{serviceId}
    // Method: PUT
    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceU> updateService(@PathVariable(name="serviceId")Long serviceId,@RequestBody ServiceU service){
        if(serviceService.getServiceById(serviceId) == null){
            return new ResponseEntity<ServiceU>(HttpStatus.NOT_FOUND);
        }

        serviceService.validateService(service);
        service.setId(serviceId);
        return new ResponseEntity<ServiceU>(serviceService.updateService(service),HttpStatus.OK);
    }


    // URL: http://localhost:8080/api/smartcontract/v1/service/{serviceId}
    // Method: DELETE
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<String> deleteService(@PathVariable(name="serviceId")Long serviceId){
        if(serviceService.getServiceById(serviceId) == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        serviceService.deleteService(serviceId);
        return new ResponseEntity<String>("Service deleted successfully",HttpStatus.OK);
    }
}
