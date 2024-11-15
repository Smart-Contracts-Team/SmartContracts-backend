package com.SmartContracts.upc.smartcontract.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class MySmartContract extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b506105578061001c5f395ff3fe608060405234801561000f575f80fd5b5060043610610055575f3560e01c8063474da79a146100595780635e9058331461008b5780636ebc8c86146100a75780638736381a146100d9578063db5271d3146100f7575b5f80fd5b610073600480360381019061006e919061031f565b610127565b60405161008293929190610359565b60405180910390f35b6100a560048036038101906100a0919061038e565b61014c565b005b6100c160048036038101906100bc919061031f565b6101c7565b6040516100d093929190610359565b60405180910390f35b6100e1610271565b6040516100ee91906103de565b60405180910390f35b610111600480360381019061010c91906103f7565b610277565b60405161011e91906103de565b60405180910390f35b5f602052805f5260405f205f91509050805f0154908060010154908060020154905083565b5f8311801561015d57506001548311155b61019c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101939061048f565b60405180910390fd5b5f805f8581526020019081526020015f20905082816001018190555081816002018190555050505050565b5f805f80841180156101db57506001548411155b61021a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102119061048f565b60405180910390fd5b5f805f8681526020019081526020015f206040518060600160405290815f8201548152602001600182015481526020016002820154815250509050805f015181602001518260400151935093509350509193909250565b60015481565b5f60015f81548092919061028a906104da565b919050555060405180606001604052806001548152602001848152602001838152505f8060015481526020019081526020015f205f820151815f01556020820151816001015560408201518160020155905050600154905092915050565b5f80fd5b5f819050919050565b6102fe816102ec565b8114610308575f80fd5b50565b5f81359050610319816102f5565b92915050565b5f60208284031215610334576103336102e8565b5b5f6103418482850161030b565b91505092915050565b610353816102ec565b82525050565b5f60608201905061036c5f83018661034a565b610379602083018561034a565b610386604083018461034a565b949350505050565b5f805f606084860312156103a5576103a46102e8565b5b5f6103b28682870161030b565b93505060206103c38682870161030b565b92505060406103d48682870161030b565b9150509250925092565b5f6020820190506103f15f83018461034a565b92915050565b5f806040838503121561040d5761040c6102e8565b5b5f61041a8582860161030b565b925050602061042b8582860161030b565b9150509250929050565b5f82825260208201905092915050565b7f436f6e747261746f206e6f20656e636f6e747261646f2e0000000000000000005f82015250565b5f610479601783610435565b915061048482610445565b602082019050919050565b5f6020820190508181035f8301526104a68161046d565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f6104e4826102ec565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610516576105156104ad565b5b60018201905091905056fea2646970667358221220acf27834a9829fd961ec0ab63ccea2b816dade741c1ea8556f94e542866f9b9b64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CONTRACTCOUNT = "contractCount";

    public static final String FUNC_CONTRACTS = "contracts";

    public static final String FUNC_CREATECONTRACT = "createContract";

    public static final String FUNC_GETCONTRACT = "getContract";

    public static final String FUNC_UPDATECONTRACT = "updateContract";

    @Deprecated
    protected MySmartContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                  BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MySmartContract(String contractAddress, Web3j web3j, Credentials credentials,
                  ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MySmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                  BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MySmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                  ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> contractCount() {
        final Function function = new Function(FUNC_CONTRACTCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> contracts(
            BigInteger param0) {
        final Function function = new Function(FUNC_CONTRACTS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createContract(BigInteger _businessId,
                                                                 BigInteger _influencerId) {
        final Function function = new Function(
                FUNC_CREATECONTRACT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_businessId),
                        new org.web3j.abi.datatypes.generated.Uint256(_influencerId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getContract(
            BigInteger _contractId) {
        final Function function = new Function(FUNC_GETCONTRACT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_contractId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateContract(BigInteger _contractId,
                                                                 BigInteger _newBusinessId, BigInteger _newInfluencerId) {
        final Function function = new Function(
                FUNC_UPDATECONTRACT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_contractId),
                        new org.web3j.abi.datatypes.generated.Uint256(_newBusinessId),
                        new org.web3j.abi.datatypes.generated.Uint256(_newInfluencerId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static MySmartContract load(String contractAddress, Web3j web3j, Credentials credentials,
                           BigInteger gasPrice, BigInteger gasLimit) {
        return new MySmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MySmartContract load(String contractAddress, Web3j web3j,
                           TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MySmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MySmartContract load(String contractAddress, Web3j web3j, Credentials credentials,
                           ContractGasProvider contractGasProvider) {
        return new MySmartContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MySmartContract load(String contractAddress, Web3j web3j,
                           TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MySmartContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MySmartContract> deploy(Web3j web3j, Credentials credentials,
                                         ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MySmartContract.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<MySmartContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
                                         BigInteger gasLimit) {
        return deployRemoteCall(MySmartContract.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<MySmartContract> deploy(Web3j web3j, TransactionManager transactionManager,
                                         ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MySmartContract.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<MySmartContract> deploy(Web3j web3j, TransactionManager transactionManager,
                                         BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MySmartContract.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }
    /*
    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }*/

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}