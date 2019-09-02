package it.protectid.jquorum;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.quorum.tx.ClientTransactionManager;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * @author ascatox
 */
public class EngClientTransactionManager extends ClientTransactionManager {

    protected Web3j web3j;

    public EngClientTransactionManager(Web3j web3j, String fromAddress, String privateFrom, List<String> privateFor, int attempts, int sleepDuration) {
        super(web3j, fromAddress, privateFrom, privateFor, attempts, sleepDuration);
        this.web3j = web3j;
    }

    public EngClientTransactionManager(Web3j web3j, String fromAddress, String privateFrom, List<String> privateFor) {
        super(web3j, fromAddress, privateFrom, privateFor);
        this.web3j = web3j;
    }

    public EngClientTransactionManager(Web3j web3j, String fromAddress, List<String> privateFor, int attempts, int sleepDuration) {
        super(web3j, fromAddress, privateFor, attempts, sleepDuration);
        this.web3j = web3j;
    }

    public EngClientTransactionManager(Web3j web3j, String fromAddress, List<String> privateFor) {
        super(web3j, fromAddress, privateFor);
        this.web3j = web3j;
    }

    public EthSendTransaction sendTransaction(BigInteger gasPrice, BigInteger gasLimit, String to, String data, BigInteger value, boolean val) throws IOException {
        return super.sendTransaction(gasPrice, gasLimit, to, data, value);
    }

    public String sendCall(String to, String data, DefaultBlockParameter defaultBlockParameter) throws IOException {
        return ((EthCall)this.web3j.ethCall(Transaction.createEthCallTransaction(this.getFromAddress(), to, data), defaultBlockParameter).send()).getValue();
    }
}
