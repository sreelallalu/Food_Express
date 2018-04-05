package com.foodexpress.app.model;

/**
 * Created by sreelal on 3/4/18.
 */

public class OrderCancel {

    String or_id;
    String item;
    String rupees;
    String or_date;

    public OrderCancel(String or_id, String item, String rupees, String or_date) {
        this.or_id = or_id;
        this.item = item;
        this.rupees = rupees;
        this.or_date = or_date;
    }

    public String getOr_id() {
        return or_id;
    }

    public void setOr_id(String or_id) {
        this.or_id = or_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRupees() {
        return rupees;
    }

    public void setRupees(String rupees) {
        this.rupees = rupees;
    }

    public String getOr_date() {
        return or_date;
    }

    public void setOr_date(String or_date) {
        this.or_date = or_date;
    }
}
