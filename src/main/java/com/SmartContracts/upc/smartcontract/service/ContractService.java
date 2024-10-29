package com.SmartContracts.upc.smartcontract.service;

import com.SmartContracts.upc.smartcontract.model.Contract;

import java.util.List;

public interface ContractService {
    public abstract List<Contract> getAllContracts();
    public abstract List<Contract> getContractsByInfluencerId(Long influencerId);
    public abstract List<Contract> getContractsByBusinessId(Long businessId);
    public abstract Contract getContractById(Long id);
    public abstract Contract createContract(Contract contract);
    public abstract Contract updateContract(Contract contract);
    public abstract void deleteContract(Long id);
    public void validateContract(Contract contract);

}
