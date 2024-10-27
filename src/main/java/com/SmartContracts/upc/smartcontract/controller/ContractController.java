package com.SmartContracts.upc.smartcontract.controller;

import com.SmartContracts.upc.smartcontract.model.RoyaltyContract;
import com.SmartContracts.upc.smartcontract.model.Transaction;
import com.SmartContracts.upc.smartcontract.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/smartcontract/v1/contract")
public class ContractController {
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/deploy")
    public RoyaltyContract deployContract(@RequestParam String creatorId, @RequestParam String contractType) {
        return contractService.deployContract(creatorId, contractType);
    }

    @PostMapping("/transfer")
    public Transaction transferRoyalty(@RequestParam String contractId, @RequestParam String toAddress,
                                           @RequestParam BigDecimal amount) {
        return contractService.transferRoyalty(contractId, toAddress, amount);
    }

    @GetMapping("/{contractId}/transactions")
    public List<Transaction> getTransactions(@PathVariable String contractId) {
        return contractService.getTransactions(contractId);
    }
}
