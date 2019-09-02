package it.protectid.jquorum;

import it.protectid.jquorum.wrapper.contract.Greeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Node;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class QuorumImpl implements Quorum {

	private static final Logger log = LoggerFactory.getLogger(QuorumImpl.class);

	private static final BigInteger GAS_PRICE = DefaultGasProvider.GAS_PRICE;
	private static final BigInteger GAS_LIMIT = DefaultGasProvider.GAS_LIMIT;


	private static final String ULR_QUORUM = "http://localhost:22000";
	private static final String SOURCE_NODE_ADDRESS = "enode://localhost:2200";
	private static final String DEST_NODE_ADDRESS = "enode://localhost:22000";

	private static final String NODE_PUBLICKEY = "";


	private String sendSync(String payload) throws IOException {
		org.web3j.quorum.Quorum quorum = org.web3j.quorum.Quorum.build(new HttpService("http://localhost:22001"));
		Web3ClientVersion web3ClientVersion = quorum.web3ClientVersion().send();
		return web3ClientVersion.getWeb3ClientVersion();
	}


	private String sendAsync(String payload) throws ExecutionException, InterruptedException {
		org.web3j.quorum.Quorum quorum = org.web3j.quorum.Quorum.build(new HttpService("http://localhost:22001"));
		Web3ClientVersion web3ClientVersion = quorum.web3ClientVersion().sendAsync().get();
		return web3ClientVersion.getWeb3ClientVersion();
	}

	private String sendPrivateTransaction(Node sourceNode, Node destNode, String sig, String payload) throws Throwable {

		log.info("Preparation of calls to Quorum network...");


		if (true) { //TODO
			org.web3j.quorum.Quorum quorum = org.web3j.quorum.Quorum.build(new HttpService(sourceNode.getUrl()));
			final EthAccounts ethAccounts = quorum.ethAccounts().send();
			final String ethFirstAccount = ethAccounts.getAccounts().get(0);
			final List<String> privateFor = sourceNode.getPublicKeys();

			EngClientTransactionManager transactionManager =
				new EngClientTransactionManager(
					quorum,
					ethFirstAccount, ethFirstAccount, privateFor);
			ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
			//Greeter.deploy(jquorum, transactionManager, contractGasProvider, "Ciao Prof. Conte").send();
			final Greeter greeter = Greeter.load("0xaa0b763cd8899196f9efce1e7b77b63c5671fbd8", quorum, transactionManager, contractGasProvider);
			final RemoteCall<String> greet = greeter.greet();
			String message = greet.send();
			System.out.println("Greeter: " + message);
		}
		// final RemoteFunctionCall<TransactionReceipt> transactionReceiptRemoteFunctionCall = contract.create(payload);
		// log.info("Remote call received... " + transactionReceiptRemoteFunctionCall.toString());
		return sig;
	}

	public String entryPointQuorum(String payload, String sig) {


		Node sourceNode = new Node(SOURCE_NODE_ADDRESS, Arrays.asList(NODE_PUBLICKEY), ULR_QUORUM);
		Node destNode = new Node(DEST_NODE_ADDRESS, Arrays.asList(NODE_PUBLICKEY), ULR_QUORUM);

		try {
			return sendPrivateTransaction(sourceNode, destNode, sig, payload);
		} catch (Throwable e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
