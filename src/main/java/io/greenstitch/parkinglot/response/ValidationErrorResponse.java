package io.greenstitch.parkinglot.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    int code;
    private List<Violation> violations = new ArrayList<>();
}
