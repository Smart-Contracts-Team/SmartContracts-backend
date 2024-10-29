package com.SmartContracts.upc.smartcontract.service.impl;

import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.smartcontract.model.Contract;
import com.SmartContracts.upc.smartcontract.repository.ContractRepository;
import com.SmartContracts.upc.smartcontract.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> getContractsByInfluencerId(Long influencerId) {
        return contractRepository.findByInfluencerId(influencerId);
    }

    @Override
    public List<Contract> getContractsByBusinessId(Long businessId) {
        return contractRepository.findByBusinessId(businessId);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {

        Contract contractToUpdate = getContractById(contract.getId());
        if(contractToUpdate != null){
            contractToUpdate.setTitle(contract.getTitle());
            contractToUpdate.setDescription(contract.getDescription());
            contractToUpdate.setPrice(contract.getPrice());
            contractToUpdate.setStartDate(contract.getStartDate());
            contractToUpdate.setFinalDate(contract.getFinalDate());
            contractToUpdate.setStatus(contract.getStatus());
            contractToUpdate.setType(contract.getType());
            contractToUpdate.setBusinessId(contract.getBusinessId());
            contractToUpdate.setInfluencerId(contract.getInfluencerId());
            contractToUpdate.setServices(contract.getServices());

            return contractRepository.save(contractToUpdate);
        }

        return null;
    }

    @Override
    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public void validateContract(Contract contract) {
        if (contract == null) {
            throw new ValidationException("El contrato no puede ser nulo");
        }
        if (contract.getTitle() == null || contract.getTitle().isEmpty()) {
            throw new ValidationException("El título del contrato es obligatorio");
        }
        if (contract.getTitle().length() > 150) {
            throw new ValidationException("El título del contrato es demasiado largo");
        }
        if (contract.getDescription() == null || contract.getDescription().isEmpty()) {
            throw new ValidationException("La descripción del contrato es obligatoria");
        }
        if (contract.getDescription().length() > 300) {
            throw new ValidationException("La descripción del contrato es demasiado larga");
        }
        if (contract.getStartDate() == null) {
            throw new ValidationException("La fecha de inicio del contrato es obligatoria");
        }
        if (contract.getFinalDate() == null) {
            throw new ValidationException("La fecha final del contrato es obligatoria");
        }
        if (contract.getFinalDate().isBefore(contract.getStartDate())) {
            throw new ValidationException("La fecha final no puede ser anterior a la fecha de inicio");
        }
        if (contract.getPrice() == null || contract.getPrice() <= 0) {
            throw new ValidationException("El precio del contrato es obligatorio y debe ser mayor a cero");
        }
        if (contract.getStatus() == null || contract.getStatus().isEmpty()) {
            throw new ValidationException("El estado del contrato es obligatorio");
        }
        if (contract.getType() == null || contract.getType().isEmpty()) {
            throw new ValidationException("El tipo de contrato es obligatorio");
        }
        if (contract.getServices() == null || contract.getServices().isEmpty()) {
            throw new ValidationException("El contrato debe tener al menos un servicio asociado");
        }
        if (contract.getBusinessId() == null){
            throw new ValidationException("El contrato debe tener una empresa asociada");
        }
        if (contract.getInfluencerId() == null){
            throw new ValidationException("El contrato debe tener un influencer asociada");
        }
    }
}
