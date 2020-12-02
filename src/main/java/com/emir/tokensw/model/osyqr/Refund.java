package com.emir.tokensw.model.osyqr;

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
public class Refund {

    private Integer returnCode;
    private String returnDesc;
    private String receiptMsgCustomer;
    private String receiptMsgMerchant;
    private String QRdata;
    private String qrData;

    public void setQRdata(String QRdata) {
        this.QRdata = QRdata;
    }

    public String getQrData() {
        return qrData;
    }
}
