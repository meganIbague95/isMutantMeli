package com.meli.challenge;

public class RequestShow {

    private String lastEvaluatedKey;

    public RequestShow(String lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }

    public String getLastEvaluatedKey() {
        return lastEvaluatedKey;
    }

    public void setLastEvaluatedKey(String lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }
}
