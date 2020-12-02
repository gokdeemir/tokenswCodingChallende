package com.emir.tokensw.service;

import com.emir.tokensw.constant.OysConstant;
import com.emir.tokensw.exception.BadRequestException;
import com.emir.tokensw.model.osyqr.BatchSummary;
import com.emir.tokensw.model.osyqr.Payment;
import com.emir.tokensw.model.osyqr.Refund;
import com.emir.tokensw.model.osyqr.Transaction;
import com.emir.tokensw.util.HtttpHeaderUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import static com.emir.tokensw.constant.ErrorConstants.SSL_CONNECTION_ERROR;

/**
 * Created by Emir Gökdemir
 * on 2 Ara 2020
 */

@Service
public class PaymentService {

    @Autowired
    private HtttpHeaderUtil headerUtil;

    public ResponseEntity<JsonNode> payWithQR( Payment payment) {
        HttpHeaders headers = headerUtil.getOysRequestHeader();
        payment.setQRdata(payment.getQrData());
        HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);
        RestTemplate restTemplate = restTemplate();
        return restTemplate.postForEntity(OysConstant.API_PAYOSY_URL + "/payment", entity, JsonNode.class);
    }

    public ResponseEntity<JsonNode> refundWithQR( Refund refund) {
        HttpHeaders headers = headerUtil.getOysRequestHeader();
        refund.setQRdata(refund.getQrData());
        HttpEntity<Refund> entity = new HttpEntity<>(refund, headers);
        RestTemplate restTemplate = restTemplate();
        return restTemplate.postForEntity(OysConstant.API_PAYOSY_URL + "/payment", entity, JsonNode.class);
    }

    public ResponseEntity<JsonNode> getBatchSummary( BatchSummary summary) {
        HttpHeaders headers = headerUtil.getOysRequestHeader();
        HttpEntity<BatchSummary> entity = new HttpEntity<>(summary, headers);
        RestTemplate restTemplate = restTemplate();
        return restTemplate.postForEntity(OysConstant.API_PAYOSY_URL + "/corridor/batchsummaries", entity, JsonNode.class);
    }

    public ResponseEntity<JsonNode> getTransaction( Transaction transaction) {
        HttpHeaders headers = headerUtil.getOysRequestHeader();
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = restTemplate();
        return restTemplate.postForEntity(OysConstant.API_PAYOSY_URL + "/corridor/transactions", entity, JsonNode.class);
    }
    public ResponseEntity<JsonNode> closeQrBatch() {
        HttpHeaders headers = headerUtil.getOysRequestHeader();
        HttpEntity<Transaction> entity = new HttpEntity<>( headers);
        RestTemplate restTemplate = restTemplate();
        return restTemplate.postForEntity(OysConstant.API_PAYOSY_URL + "/get_qr_batchclose", entity, JsonNode.class);
    }

      public RestTemplate restTemplate()
            {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

                SSLContext sslContext;
                try {
                    sslContext = org.apache.http.ssl.SSLContexts.custom()
                            .loadTrustMaterial(null, acceptingTrustStrategy)
                            .build();
                } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                   throw new BadRequestException(SSL_CONNECTION_ERROR);
                }

                SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }
}
