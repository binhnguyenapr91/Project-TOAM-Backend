package edu.codegym.toam;

import org.springframework.format.annotation.DateTimeFormat;

public class ValuePerMonth {
    @DateTimeFormat(pattern = "MM/yyyy")
    private String dateAndMonth;
    private long quantityOfContracts;
    private float valuePerMonth;

    public String getDateAndMonth() {
        return dateAndMonth;
    }

    public void setDateAndMonth(String dateAndMonth) {
        this.dateAndMonth = dateAndMonth;
    }

    public long getQuantityOfContracts() {
        return quantityOfContracts;
    }

    public void setQuantityOfContracts(long quantityOfContracts) {
        this.quantityOfContracts = quantityOfContracts;
    }

    public float getValuePerMonth() {
        return valuePerMonth;
    }

    public void setValuePerMonth(float valuePerMonth) {
        this.valuePerMonth = valuePerMonth;
    }
}
