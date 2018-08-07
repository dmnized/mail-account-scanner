package com.example.mailaccountscanner.config;

import java.text.SimpleDateFormat;

public class Constants {

    public static final String RECEIPT_DAY_VALIDATION_PATTERN = "^((19|20)\\d\\d)(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])$";
    public static final SimpleDateFormat RECEIPT_DAY_FORMAT = new SimpleDateFormat("yyyyMMdd");

}
