package com.emir.tokensw.controller;

import com.emir.tokensw.model.osyqr.BatchSummary;
import com.emir.tokensw.model.osyqr.Payment;
import com.emir.tokensw.model.osyqr.Refund;
import com.emir.tokensw.model.osyqr.Transaction;
import com.emir.tokensw.service.PaymentService;
import com.emir.tokensw.service.QRCodeService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Emir Gökdemir
 * on 1 Ara 2020
 */

@RestController
@RequestMapping("/payment")
@Api(value = "Payment", tags = {"Payment Operations"})
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService service;


    @ApiOperation(value = "Pay with the generated qr code returns api response", response = JsonNode.class)
    @PostMapping("/pay-with-qr")
    public ResponseEntity<JsonNode> payWithQR( @RequestBody Payment payment){
        logger.info(String.format("Requesting payWithQR with qr: %s.", payment.getQrData()));
        return service.payWithQR(payment);
    }

    @ApiOperation(value = "Refund with the generated qr code returns api response", response = JsonNode.class)
    @PostMapping("/refund-with-qr")
    public ResponseEntity<JsonNode> refundWithQR( @RequestBody Refund refund){
        logger.info(String.format("Requesting refundWithQR with qr: %s.", refund.getQrData()));
        return service.refundWithQR(refund);
    }

    @ApiOperation(value = "Get Batch Summary with the posID and  batchNumber returns api response", response = JsonNode.class)
    @PostMapping("/get-batch-summary")
    public ResponseEntity<JsonNode> getBatchSummary( @RequestBody BatchSummary batchSummary){
        logger.info(String.format("Requesting getBatchSummary with posID: %s.", batchSummary.getPosID()));
        return service.getBatchSummary(batchSummary);
    }

    @ApiOperation(value = "Get Transaction with the sessionID, posID and  batchNumber returns api response", response = JsonNode.class)
    @PostMapping("/get-transaction")
    public ResponseEntity<JsonNode> getTransaction( @RequestBody Transaction transaction){
        logger.info(String.format("Requesting getTransaction with sessionID: %s.", transaction.getSessionID()));
        return service.getTransaction(transaction);
    }

    @ApiOperation(value = "Close Qr batch", response = JsonNode.class)
    @PostMapping("/close-qr-batch")
    public ResponseEntity<JsonNode> getTransaction(){
        logger.info("Requesting closeQrBatch");
        return service.closeQrBatch();
    }

    @ApiOperation(value = "Read the qr code image and take text by giving path of image", response = String.class)
    @PostMapping("/get-qr")
    public ResponseEntity<String> readQrText(@RequestPart MultipartFile file){
        logger.info(String.format("Requesting readQrText with name: %s.", file.getName()));
        return ResponseEntity.ok(QRCodeService.readQR(file));
    }

}
