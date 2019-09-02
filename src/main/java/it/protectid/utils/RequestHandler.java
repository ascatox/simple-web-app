package it.protectid.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;



public class RequestHandler {


    private Map<String, String> getParamsInfo(HttpServletRequest request, HttpResponse response) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getParameterNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }


        return map;
    }

    private HttpResponse manageAndSendRequest(Map<String, String> reqMap, HttpResponse response) {

        String keyState = "state";
        String valueState = String.valueOf(LocalDateTime.now().hashCode());
        reqMap.put(keyState, valueState);

        response.setEntity((HttpEntity) reqMap);
        return response;


    }


}

