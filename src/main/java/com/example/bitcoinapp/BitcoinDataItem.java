package com.example.bitcoinapp;

public class BitcoinDataItem {
    private Long x;
    private Double y;

    public BitcoinDataItem() {
    }

    public BitcoinDataItem(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
