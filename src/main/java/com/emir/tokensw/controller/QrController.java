package com.emir.tokensw.controller;

import com.emir.tokensw.service.QRCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Emir Gökdemir
 * on 2 Ara 2020
 */

@RestController
@RequestMapping("/qr")
@Api(value = "QR", tags = {"QR Operations"})
public class QrController {

    private static final Logger logger = LoggerFactory.getLogger(QrController.class);

    @ApiOperation(value = "Read the qr code image and take text by giving path of image", response = String.class)
    @PostMapping("/read")
    public ResponseEntity<String> readQrText(@RequestPart MultipartFile file){
        logger.info(String.format("Requesting readQrText with name: %s.", file.getName()));
        return ResponseEntity.ok(QRCodeService.readQR(file));
    }
}
