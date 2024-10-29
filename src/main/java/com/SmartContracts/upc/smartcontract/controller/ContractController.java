package com.SmartContracts.upc.smartcontract.controller;

import com.SmartContracts.upc.smartcontract.model.Contract;
import com.SmartContracts.upc.smartcontract.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/smartcontract/v1/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService=contractService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/contract
    // Method: GET

    @Transactional
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts(){
        if(contractService.getAllContracts().isEmpty()){
            return new ResponseEntity<List<Contract>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contract>>(contractService.getAllContracts(),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/contract/{contractId}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/{contractId}")
    public ResponseEntity<Contract> getContractById(@PathVariable(name="contractId")Long contractId){
        if(contractService.getContractById(contractId)==null){
            return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contract>(contractService.getContractById(contractId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/contract/influencer/{influencerId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<Contract>> getContractsByInfluencerId(@PathVariable(name="influencerId")Long influencerId){
        if(contractService.getContractsByInfluencerId(influencerId)==null){
            return new ResponseEntity<List<Contract>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Contract>>(contractService.getContractsByInfluencerId(influencerId),HttpStatus.OK);
    }


    // URL: http://localhost:8080/api/smartcontract/v1/contract/business/{businessId}
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Contract>> getContractsByBusinessId(@PathVariable(name="businessId")Long businessId){
        if(contractService.getContractsByBusinessId(businessId)==null){
            return new ResponseEntity<List<Contract>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Contract>>(contractService.getContractsByBusinessId(businessId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/contract
    // Method: POST

    @Transactional
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract){
        contractService.validateContract(contract);

        return new ResponseEntity<Contract>(contractService.createContract(contract),HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/contract/{contractId}
    // Method: PUT

    @PutMapping("/{contractId}")
    public ResponseEntity<Contract> updateContract(@PathVariable(name="contractId")Long contractId,@RequestBody Contract contract){
        if(contractService.getContractById(contractId) == null)

        contractService.validateContract(contract);
        return new ResponseEntity<Contract>(contractService.updateContract(contract),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/contract/{contractId}
    // Method: DELETE

    @DeleteMapping("/{contractId}")
    public ResponseEntity<String> deleteContract(@PathVariable(name="contractId")Long contractId){
        if(contractService.getContractById(contractId) == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        contractService.deleteContract(contractId);
        return new ResponseEntity<String>("Contract deleted successfully",HttpStatus.OK);
    }
}
