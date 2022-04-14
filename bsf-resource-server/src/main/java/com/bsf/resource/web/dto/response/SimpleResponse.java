package com.bsf.resource.web.dto.response;

import lombok.Data;

@Data
public class SimpleResponse {
    private String transactionStatus;
    private String statusMessage;

    /**
     * @param transactionStatus
     * @param statusMessage
     */
    public SimpleResponse(String transactionStatus, String statusMessage) {
        this.transactionStatus = transactionStatus;
        this.statusMessage = statusMessage;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "SimpleResponse{" +
                "transactionStatus='" + transactionStatus + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }

}
