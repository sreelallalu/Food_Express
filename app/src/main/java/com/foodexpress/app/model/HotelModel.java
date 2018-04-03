package com.foodexpress.app.model;

/**
 * Created by sreelal on 1/4/18.
 */

public class HotelModel {
    String name;
    String email;
    String items;
    String id;


    public HotelModel(String name, String email, String items, String id) {
        this.name = name;
        this.email = email;
        this.items = items;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
