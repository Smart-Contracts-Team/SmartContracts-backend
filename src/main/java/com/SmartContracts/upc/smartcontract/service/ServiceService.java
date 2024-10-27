package com.SmartContracts.upc.smartcontract.service;

import com.SmartContracts.upc.smartcontract.model.ServiceU;

import java.util.List;

public interface ServiceService {
    public abstract List<ServiceU> getAllServices();
    public abstract List<ServiceU> getServiceByUserId(Long id);
    public abstract List<ServiceU> getServiceByCategory(String category);
    public abstract ServiceU getServiceById(Long id);
    public abstract ServiceU createService(ServiceU service);
    public abstract ServiceU updateService(ServiceU service);
    public abstract void deleteService(Long id);
    public void validateService(ServiceU service);
}
