package com.SmartContracts.upc.smartcontract.service;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Service
public class EthereumService {

    private Web3j web3j;

    public EthereumService() {
        // Conectar a la red Sepolia de Infura
        String infuraSepoliaUrl = "https://sepolia.infura.io/v3/db5598bb5ad84359b2cdae1890b9407e";
        this.web3j = Web3j.build(new HttpService(infuraSepoliaUrl));
    }

    // Método para verificar la conexión
    public String getNetworkId() throws Exception {
        return web3j.ethChainId().send().getChainId().toString();
    }
}