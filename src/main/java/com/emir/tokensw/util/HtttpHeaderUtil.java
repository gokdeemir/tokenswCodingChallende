package com.emir.tokensw.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by Emir Gökdemir
 * on 2 Ara 2020
 */
@Component
public class HtttpHeaderUtil {

    @Value("${tokensw.x-ibm-client-secret}")
    private String secret;

    @Value("${tokensw.x-ibm-client-id}")
    private String id;

    public HttpHeaders getOysRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("x-ibm-client-id", id);
        headers.add("x-ibm-client-secret", secret);
        return headers;
    }
}
