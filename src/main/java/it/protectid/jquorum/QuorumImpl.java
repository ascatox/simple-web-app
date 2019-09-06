package it.protectid.jquorum;

import it.protectid.jquorum.wrapper.DIR;
import it.protectid.jquorum.wrapper.PPL;
import it.protectid.model.authority.Pdc;
import it.protectid.model.authority.Pip;
import it.protectid.model.policy.Ppa;
import it.protectid.model.policy.Ppm;
import it.protectid.utils.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Node;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class QuorumImpl implements Quorum {


    private static final Logger log = LoggerFactory.getLogger(QuorumImpl.class);

    private static final BigInteger GAS_PRICE = DefaultGasProvider.GAS_PRICE;
    private static final BigInteger GAS_LIMIT = DefaultGasProvider.GAS_LIMIT;

    private static final String ULR_QUORUM = "http://localhost:22000";
    private static final String SOURCE_NODE_ADDRESS = "enode://localhost:2200";
    private static final String DEST_NODE_ADDRESS = "enode://localhost:22000";

    private static final String NODE_PUBLICKEY = "";

    private static Node sourceNode = new Node(SOURCE_NODE_ADDRESS, Arrays.asList(NODE_PUBLICKEY), ULR_QUORUM);
    private static Node destNode = new Node(DEST_NODE_ADDRESS, Arrays.asList(NODE_PUBLICKEY), ULR_QUORUM);

    private static JsonHandler jsonHandler;

    public static String getUlrQuorum() {
        return ULR_QUORUM;
    }

    public static String getSourceNodeAddress() {
        return SOURCE_NODE_ADDRESS;
    }

    public static String getDestNodeAddress() {
        return DEST_NODE_ADDRESS;
    }

    public static String getNodePublickey() {
        return NODE_PUBLICKEY;
    }

    private List initQorum(Node sourceNode, Node destNode) throws Throwable {

        log.info("Preparation of calls to Quorum network...");

        org.web3j.quorum.Quorum quorum = org.web3j.quorum.Quorum.build(new HttpService(sourceNode.getUrl()));
        final EthAccounts ethAccounts = quorum.ethAccounts().send();
        final String ethFirstAccount = ethAccounts.getAccounts().get(0);
        final List<String> privateFor = sourceNode.getPublicKeys();

        EngClientTransactionManager transactionManager =
                new EngClientTransactionManager(
                        quorum,
                        ethFirstAccount, ethFirstAccount, privateFor);
        ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
        List listQuorum = new ArrayList();
        listQuorum.add(quorum);
        listQuorum.add(transactionManager);
        listQuorum.add(contractGasProvider);
        return listQuorum;
    }

    @Override
    public String insertPIP(Pip pip, String sig) {
        return null;
    }

    @Override
    public String deletePIP(Pip pip, String sig) {
        return null;
    }

    @Override
    public Collection<Pip> getPIP() {
        List quorumInfo = null;
        try {
            quorumInfo = initQorum(sourceNode, destNode);

            org.web3j.quorum.Quorum quorum = (org.web3j.quorum.Quorum) quorumInfo.get(0);
            EngClientTransactionManager transactionManager = (EngClientTransactionManager) quorumInfo.get(1);
            ContractGasProvider contractGasProvider = (ContractGasProvider) quorumInfo.get(2);
            final DIR dir = DIR.load("0xaa0b763cd8899196f9efce1e7b77b63c5671fbd8", quorum, transactionManager, contractGasProvider);
            final RemoteFunctionCall<TransactionReceipt> remoteFunctionCall=  dir.getPIP();
            remoteFunctionCall.send();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public Pip getPIPById(String id, String sig) {
        return null;
    }

    public String insertPPM(Ppm ppm, String sig) {

        //TODO check sig
        try {
            List quorumInfo = initQorum(sourceNode, destNode);
            org.web3j.quorum.Quorum quorum = (org.web3j.quorum.Quorum) quorumInfo.get(0);
            EngClientTransactionManager transactionManager = (EngClientTransactionManager) quorumInfo.get(1);
            ContractGasProvider contractGasProvider = (ContractGasProvider) quorumInfo.get(2);
            final PPL ppl = PPL.load("0xaa0b763cd8899196f9efce1e7b77b63c5671fbd8", quorum, transactionManager, contractGasProvider);
            final RemoteFunctionCall<TransactionReceipt> insert = ppl.insertPPM(ppm.getId(), ppm.getDp(), ppm.getModel());
            String message = insert.encodeFunctionCall();
            System.out.println("Return: " + message);
            return message;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public String deletePPM(Ppm ppm, String sig) {
        return null;
    }

    public String insertPPA(Ppa ppa) {
        return null;
    }

    public String deletePPA(Ppa ppa, String sig) {
        return null;
    }

    public String insertPDC(Pdc pdc) {
        return null;
    }

    public String deletePDC(Pdc pdc, String id) {
        return null;
    }

}
