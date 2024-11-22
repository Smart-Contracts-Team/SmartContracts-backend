package com.SmartContracts.upc.smartcontract.service;

import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.smartcontract.model.MySmartContract;
import com.SmartContracts.upc.smartcontract.model.SmartContractInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.abi.datatypes.Type;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class EthereumContractService {

    private final Web3j web3j;
    private final MySmartContract contract;

    // Constructor
    @Autowired
    public EthereumContractService(Web3j web3j) {
        this.web3j = web3j;

        // Dirección de tu contrato y clave privada de la cuenta
        String contractAddress = "0xb46d72b7c5d261c88afd7538ef38a1ec953ade33"; // Reemplaza con tu dirección de contrato
        String rawContractAddress = Keys.toChecksumAddress(contractAddress); // Verificar dirección checksummed
        System.out.println("Contract Address (Checksum): " + rawContractAddress);

        String privateKey = "eb06a392d30f77ef985bb36c36c7f8aa47e79c63d3c24fcdb2a811ae46e1fe13"; // clave privada

        // Obtén el gasPrice actual de la red
        BigInteger gasPrice;
        try {
            gasPrice = web3j.ethGasPrice().send().getGasPrice();
            System.out.println("Gas Price: " + gasPrice); // Mostrar el gasPrice
        } catch (Exception e) {
            gasPrice = DefaultGasProvider.GAS_PRICE;
            System.out.println("Error obteniendo el gasPrice, usando valor por defecto: " + gasPrice);
        }

        // Configura el gasLimit basado en el contrato (puedes ajustarlo si es necesario)
        BigInteger gasLimit = BigInteger.valueOf(300000);

        // Inicializar Web3j y el contrato
        ContractGasProvider gasProvider = new DefaultGasProvider();
        this.contract = MySmartContract.load(
                contractAddress,
                web3j,
                Credentials.create(privateKey),
                gasProvider
        );
    }

    // Método para llamar a la función 'createContract' del contrato
    public SmartContractInfo createContract(BigInteger businessId, BigInteger influencerId, String userAddress) throws Exception {
        // Verificar el saldo del usuario antes de proceder con la transacción
        BigInteger balance = getBalance(userAddress);
        //System.out.println("Saldo disponible: " + balance);

        // Obtener el precio y límite de gas para calcular el costo estimado de la transacción
        BigInteger gasPrice = contract.getGasPrice();
        BigInteger gasLimit = BigInteger.valueOf(300000); // Ajusta según los requisitos específicos del contrato
        BigInteger estimatedTransactionCost = gasPrice.multiply(gasLimit);
        //System.out.println("Costo estimado de la transacción: " + estimatedTransactionCost);

        // Verificar si el saldo es suficiente para cubrir el costo de la transacción
        if (balance.compareTo(estimatedTransactionCost) < 0) {
            throw new Exception("Fondos insuficientes para cubrir el gas de la transacción.");
        }

        try {
            // Crear una llamada remota para la función createContract en el contrato inteligente
            RemoteFunctionCall<TransactionReceipt> transaction = contract.createContract(businessId, influencerId);

            // Mostrar el gasPrice y gasLimit antes de enviar la transacción para depuración
            //System.out.println("Gas Price (enviar transacción): " + contract.getGasPrice());
            //System.out.println("Gas Limit (enviar transacción): " + gasLimit);

            // Enviar la transacción
            TransactionReceipt receipt = transaction.send();
            //System.out.println("Recibo de transacción: " + receipt.getTransactionHash());

            // Obtener el gas utilizado de la transacción
            BigInteger gasUsed = receipt.getGasUsed();

            return new SmartContractInfo(receipt.getTransactionHash(), receipt.getStatus(), gasUsed);

        } catch (Exception e) {
            //System.out.println("Error al enviar la transacción: " + e.getMessage());
            throw new Exception("Transacción fallida: " + e.getMessage(), e);
        }
    }


    // Método para obtener el balance de una dirección
    public BigInteger getBalance(String userAddress) throws Exception {
        try {
            EthGetBalance balance = web3j.ethGetBalance(userAddress, DefaultBlockParameterName.LATEST).send();
            return balance.getBalance(); // Muestra el saldo de la cuenta
        } catch (Exception e) {
            throw new Exception("Error al obtener el balance", e);
        }
    }

    public ArrayList<BigInteger> getContractDetails(BigInteger contractId) throws Exception {
        try {
            // Llamada al contrato inteligente para obtener los datos
            Tuple3<BigInteger, BigInteger, BigInteger> result = contract.getContract(contractId).send();

            // Create a list of Type for compatibility with executeCallMultipleValueReturn
            List<Type> resultList = new ArrayList<>();
            resultList.add(new Uint256(result.getValue1()));  // contractId
            resultList.add(new Uint256(result.getValue2()));  // businessId
            resultList.add(new Uint256(result.getValue3()));  // influencerId

            // Create an ArrayList to return the values as BigInteger
            ArrayList<BigInteger> contractInfo = new ArrayList<>();
            contractInfo.add((BigInteger) resultList.get(0).getValue());  // contractId
            contractInfo.add((BigInteger) resultList.get(1).getValue());  // businessId
            contractInfo.add((BigInteger) resultList.get(2).getValue());  // influencerId

            return contractInfo;

        } catch (Exception e) {
            System.out.println("Error al obtener el contrato: " + e.getMessage());
            throw new Exception("No se pudo obtener el contrato: " + e.getMessage(), e);
        }
    }
    // Método para llamar a la función 'updateContract' del contrato
    public SmartContractInfo updateContract(BigInteger contractId, BigInteger newBusinessId, BigInteger newInfluencerId,String userAddress) throws Exception {
        // Verificar el saldo del usuario antes de proceder con la transacción
        BigInteger balance = getBalance(userAddress);
        BigInteger gasPrice = contract.getGasPrice();
        BigInteger gasLimit = BigInteger.valueOf(300000);
        BigInteger estimatedTransactionCost = gasPrice.multiply(gasLimit);

        // Verificar si el saldo es suficiente para cubrir el costo de la transacción
        if (balance.compareTo(estimatedTransactionCost) < 0) {
            throw new Exception("Fondos insuficientes para cubrir el gas de la transacción.");
        }

        try {
            // Crear una llamada remota para la función updateContract en el contrato inteligente
            RemoteFunctionCall<TransactionReceipt> transaction = contract.updateContract(contractId, newBusinessId, newInfluencerId);

            // Enviar la transacción
            TransactionReceipt receipt = transaction.send();

            // Obtener el gas utilizado de la transacción
            BigInteger gasUsed = receipt.getGasUsed();

            return new SmartContractInfo(receipt.getTransactionHash(), receipt.getStatus(), gasUsed);

        } catch (Exception e) {
            throw new Exception("Transacción fallida: " + e.getMessage(), e);
        }
    }

}
