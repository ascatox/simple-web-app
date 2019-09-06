package it.protectid.service;

import it.protectid.crypto.AsymmetricCryptography;
import it.protectid.crypto.GenerateKeys;
import it.protectid.jquorum.QuorumImpl;
import it.protectid.model.policy.Ppm;
import it.protectid.utils.JsonHandler;
import javafx.util.Pair;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class Census {

    final String urlPPL = "http://pip...";
    private static JsonHandler jsonHandler;
    private static QuorumImpl quorum;

    public String publishPolicy() {



        String model = jsonHandler.read();
        Pair<String, Ppm> ppmPair = createPpm("", model);//TODO params
        if (ppmPair.getKey() != null && ppmPair.getValue() != null)
            return quorum.insertPPM(ppmPair.getValue(), ppmPair.getKey());
        else return null;

    }


    private Pair<String, Ppm> createPpm(String addr, String model) {

        try {
            Ppm ppm = new Ppm();
            ppm.setDp(addr);
            ppm.setModel(model);
            ppm.setId(String.valueOf(Math.random()));
            GenerateKeys gk = new GenerateKeys(1024);
            gk.createKeys();
            final PrivateKey sk = gk.getPrivateKey();
            final PublicKey pk = gk.getPublicKey();
            AsymmetricCryptography ac = new AsymmetricCryptography();
            final String sig = ac.encryptText(ppm.toString(), sk);
            Pair<String, Ppm> ppmPair = new Pair<>(sig, ppm);
            return ppmPair;

        } catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | NoSuchProviderException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
