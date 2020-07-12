package com.target.myretail.domain;

public class CurrentPrice {
    private double price;
    private String currencyCode;

    public CurrentPrice(double price, String currencyCode) {
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
