package com.SmartContracts.upc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {
    @Bean
    public Web3j web3j() {
        // URL de Infura para la red Sepolia
        String infuraUrl = "https://sepolia.infura.io/v3/db5598bb5ad84359b2cdae1890b9407e";

        // Crear una conexión Web3j con Infura
        return Web3j.build(new HttpService(infuraUrl));
    }
}

