package it.protectid.service.dir;

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

	static final String URL_SAWTOOTH = "http://localhost:8080/sawtooth";

	public enum Function {
		getPIP, insertPIP, deletePIP, getDP, insertDP, deleteDP, getSID, insertSID, deleteSID, getPPM, insertPPM, deletePPM, getPPA, insertPPA, deletePPA, getPDC, insertPDC, deletePDC
	}

	public String invoke(String fcn, String payloadJson) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestBody = new HttpEntity<>(payloadJson, httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(URL_SAWTOOTH, requestBody, String.class);
	}
}
