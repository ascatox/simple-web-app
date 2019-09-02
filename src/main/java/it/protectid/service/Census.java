package it.protectid.service;

import it.protectid.crypto.AsymmetricCryptography;
import it.protectid.crypto.GenerateKeys;
import it.protectid.policy.PolicyRequest;
import it.protectid.policy.Ppm;
import it.protectid.utils.JsonHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class Census {

    final String urlPPL = "http://pip...";
    JsonHandler jsonHandler;

    public enum Function {
        getPPM, insertPPM, deletePPM, getPPA, insertPPA, deletePPA, getPDC, insertPDC, deletePDC
    }

    public String publishPolicy() {

        String model = jsonHandler.read();

        Ppm ppm = createPpm("", model);//TODO params
        PolicyRequest payloadJson = new PolicyRequest(ppm, Function.insertPPM.name());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PolicyRequest> requestBody = new HttpEntity<>(payloadJson, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(urlPPL, requestBody, String.class);

    }


    private Ppm createPpm(String addr, String model) {

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
            ppm.setSig(sig);
            return ppm;

        } catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | NoSuchProviderException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
