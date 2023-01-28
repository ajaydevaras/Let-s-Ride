package com.nxtwave.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {
    private int statusCode;
    private String errorMessage;
    private String timeStamp;
}
