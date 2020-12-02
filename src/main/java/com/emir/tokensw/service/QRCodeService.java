package com.emir.tokensw.service;

import com.emir.tokensw.exception.BadRequestException;
import com.emir.tokensw.exception.NotFoundException;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Emir Gökdemir
 * on 1 Ara 2020
 */

public class QRCodeService {

    // Function to read the QR file
    public static String readQR(MultipartFile file) {
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(ImageIO.read(file.getInputStream()))));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (com.google.zxing.NotFoundException nfe){
            throw new NotFoundException("QR code cannot be read!");
        } catch (IOException ioe){
            throw new BadRequestException("File of QR code can not be found!");
        }
    }

}
