package com.canteko.wootaxi.requests;

public class VerifyApiKeyRequest {
    private String apiKey;
    private String email;

    public VerifyApiKeyRequest() {
    }

    public VerifyApiKeyRequest(String apiKey, String email) {
        this.apiKey = apiKey;
        this.email = email;
    }
}
