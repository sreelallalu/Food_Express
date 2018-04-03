package com.foodexpress.app.model;

/**
 * Created by sreelal on 1/4/18.
 */

public class ItemModel {
    String name;
    String rupees;

    public ItemModel(String name, String rupees) {
        this.name = name;
        this.rupees = rupees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRupees() {
        return rupees;
    }

    public void setRupees(String rupees) {
        this.rupees = rupees;
    }
}
