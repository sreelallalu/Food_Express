package com.foodexpress.app.model;

/**
 * Created by sreelal on 2/4/18.
 */

public class ItemoderModel {



    String or_id;
    String or_hotelid;
    String or_userid;
    String or_address;
    String or_delivery;
    String or_date;
    String item;

    public ItemoderModel(String or_id, String or_hotelid, String or_userid, String or_address, String or_delivery, String or_date,String item) {
        this.or_id = or_id;
        this.or_hotelid = or_hotelid;
        this.or_userid = or_userid;
        this.or_address = or_address;
        this.or_delivery = or_delivery;
        this.or_date = or_date;
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ItemoderModel() {
    }

    public String getOr_id() {
        return or_id;
    }

    public void setOr_id(String or_id) {
        this.or_id = or_id;
    }

    public String getOr_hotelid() {
        return or_hotelid;
    }

    public void setOr_hotelid(String or_hotelid) {
        this.or_hotelid = or_hotelid;
    }

    public String getOr_userid() {
        return or_userid;
    }

    public void setOr_userid(String or_userid) {
        this.or_userid = or_userid;
    }

    public String getOr_address() {
        return or_address;
    }

    public void setOr_address(String or_address) {
        this.or_address = or_address;
    }

    public String getOr_delivery() {
        return or_delivery;
    }

    public void setOr_delivery(String or_delivery) {
        this.or_delivery = or_delivery;
    }

    public String getOr_date() {
        return or_date;
    }

    public void setOr_date(String or_date) {
        this.or_date = or_date;
    }
}
