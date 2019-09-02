package it.protectid.service.dir;

import it.protectid.jquorum.Quorum;
import it.protectid.jquorum.QuorumImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author clod16
 */
@Service
public class DIRService {
    Quorum quorum;


    static final String URL_QUORUM = "http://localhost:22000";

    public enum Function {
        getPIP, insertPIP, deletePIP, getDP, insertDP, deleteDP, getSID, insertSID, deleteSID, getPPM, insertPPM, deletePPM, getPPA, insertPPA, deletePPA, getPDC, insertPDC, deletePDC
    }

    public String invoke(String fcn, String payloadJson) {
        try {
            quorum = new QuorumImpl();
            return quorum.entryPointQuorum("", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
