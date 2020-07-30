package edu.codegym.toam;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MonthValue {
    @DateTimeFormat(pattern = "MM/yyyy")
    private Date dateByMonth;
    private long quantityOfContracts;
    private float valuePerMonth;

    public Date getDateByMonth() {
        return dateByMonth;
    }

    public void setDateByMonth(Date dateByMonth) {
        this.dateByMonth = dateByMonth;
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
