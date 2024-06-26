package com.claims_management.infra.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String e) {
        super(e);
    }
}
