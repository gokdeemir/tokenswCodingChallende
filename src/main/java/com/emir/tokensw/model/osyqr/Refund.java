package com.emir.tokensw.model.osyqr;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("QRdata")
    private String qrData;
}
