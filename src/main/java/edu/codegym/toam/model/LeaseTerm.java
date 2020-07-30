package edu.codegym.toam.model;

import java.util.Date;

public class LeaseTerm {
    Date checkinDate;
    Date checkoutDate;

    public LeaseTerm(Date checkinDate, Date checkiutDate) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkiutDate;
    }

    public LeaseTerm() {
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
