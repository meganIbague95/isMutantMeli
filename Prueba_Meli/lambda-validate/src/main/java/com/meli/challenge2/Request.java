package com.meli.challenge2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty("dna")
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
