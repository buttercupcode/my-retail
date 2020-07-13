package com.target.myretail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {
    private int id;
    private String name;
    @JsonProperty("current_price")
    private CurrentPrice currentPrice;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrentPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(CurrentPrice currentPrice) {
        this.currentPrice = currentPrice;
    }
}
