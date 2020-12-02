package com.emir.tokensw.model.osyqr;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Emir Gökdemir
 * on 2 Ara 2020
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Integer returnCode;
    private String returnDesc;
    private String receiptMsgCustomer;
    private String receiptMsgMerchant;
    private JsonNode paymentInfoList;
    private String qrData;
    private String QRdata;

    public void setQRdata(String QRdata) {
        this.QRdata = QRdata;
    }
}
