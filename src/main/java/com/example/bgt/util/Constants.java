package com.example.bgt.util;

public class Constants {
    public static final int DAYS_RECLAMO = 5;
    public static final int PETICION = 1;
    public static final String PETICION_NAME = "Petición";
    public static final int QUEJA = 2;
    public static final String QUEJA_NAME = "Queja";
    public static final int RECLAMO = 3;
    public static final String RECLAMO_NAME = "Reclamo";
    public static final String ERROR_USERNAME = "Error username PQR";
    public static final String ERROR_TEXT = "Error text PQR";
    public static final String ERROR_RELATED_ID = "Error related id PQR " + RECLAMO_NAME;
    public static final String ERROR_TYPE =
            "Error type PQR: (" + PETICION_NAME + "=" + PETICION + ", " + QUEJA_NAME + "=" + QUEJA + ", "
                    + RECLAMO_NAME + "=" + RECLAMO + ")";
}
