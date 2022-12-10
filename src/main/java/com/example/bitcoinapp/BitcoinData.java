package com.example.bitcoinapp;


import java.util.List;

public class BitcoinData {
    private String status;
    private String name;
    private String unit;
    private String period;
    private String description;
    private List<BitcoinDataItem> values;

    public BitcoinData() {
    }

    public BitcoinData(String status, String name, String unit, String period, String description, List<BitcoinDataItem> values) {
        this.status = status;
        this.name = name;
        this.unit = unit;
        this.period = period;
        this.description = description;
        this.values = values;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BitcoinDataItem> getValues() {
        return values;
    }

    public void setValues(List<BitcoinDataItem> values) {
        this.values = values;
    }
}
