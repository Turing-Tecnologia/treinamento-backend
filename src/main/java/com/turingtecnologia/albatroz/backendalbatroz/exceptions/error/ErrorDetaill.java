package com.turingtecnologia.albatroz.backendalbatroz.exceptions.error;

import lombok.Data;

@Data
public class ErrorDetaill {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;
}
