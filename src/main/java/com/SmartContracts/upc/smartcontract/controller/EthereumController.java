package com.SmartContracts.upc.smartcontract.controller;

import com.SmartContracts.upc.smartcontract.model.ContractDto;
import com.SmartContracts.upc.smartcontract.service.EthereumContractService;
import com.SmartContracts.upc.smartcontract.service.EthereumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("api/smartcontract/v1/ethereum")
public class EthereumController{
    private final EthereumService ethereumService;
    private final EthereumContractService ethereumContractService;

    public EthereumController(EthereumService ethereumService, EthereumContractService ethereumContractService){
        this.ethereumService = ethereumService;
        this.ethereumContractService = ethereumContractService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/ethereum/network
    // Method: GET

    @Transactional
    @GetMapping("/network")
    public String getNetworkId(){
        try {
            return "Connected to Ethereum network with ID: " + ethereumService.getNetworkId();
        } catch (Exception e) {
            return "Failed to connect: " + e.getMessage();
        }
    }

    // URL: http://localhost:8080/api/smartcontract/v1/ethereum/smartcontract
    // Method: POST
    @Transactional
    @PostMapping("/smartcontract")
    public ResponseEntity<String> createSmartContract(@RequestBody ContractDto contractDto){
        try {


            BigInteger businessId = BigInteger.valueOf(contractDto.getBusinessId().longValue());
            BigInteger influencerId = BigInteger.valueOf(contractDto.getInfluencerId().longValue());


            String messageSuccess = ethereumContractService.createContract(businessId, influencerId,"" +
                    "0xfE1F769BBd37f29BDC99F79e038A0652881a9aB1");

            return new ResponseEntity<String>(messageSuccess, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // URL: http://localhost:8080/api/smartcontract/v1/ethereum/smartcontract/balance/{userAddress}
    // Method: GET
    @Transactional
    @GetMapping("/balance/{userAddress}")
    public ResponseEntity<BigInteger> getBalance(@PathVariable String userAddress) {
        try {
            return new ResponseEntity<BigInteger>(ethereumContractService.getBalance(userAddress),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BigInteger>(BigInteger.ZERO,HttpStatus.NOT_FOUND);
        }
    }

}