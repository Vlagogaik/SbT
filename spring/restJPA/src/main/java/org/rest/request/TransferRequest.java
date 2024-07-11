package org.rest.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private long clientId;
    private long cartId;
    private BigDecimal amount;
}
