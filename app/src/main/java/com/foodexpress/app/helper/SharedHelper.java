package com.foodexpress.app.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sreelal on 14/12/17.
 */
public class SharedHelper {
    private SharedPreferences mSharedPreferences;

    public SharedHelper(Context context) {

        this.mSharedPreferences = context.getSharedPreferences(SHAREVALUE.main, Context.MODE_PRIVATE);
    }


    public void setRegType(String centerid) {
        mSharedPreferences.edit().putString(SHAREVALUE.regtype, centerid).apply();
    }





    public void setHotelDetails(String id,String name,String email,String items) {
        mSharedPreferences.edit().putString(SHAREVALUE.hotel_id, id).apply();
        mSharedPreferences.edit().putString(SHAREVALUE.hotel_name, name).apply();
        mSharedPreferences.edit().putString(SHAREVALUE.hotel_email, email).apply();
        mSharedPreferences.edit().putString(SHAREVALUE.hotel_items, items).apply();


    }public void setUserDetails(String id,String name,String email,String mobile) {
        mSharedPreferences.edit().putString(SHAREVALUE.user_id, id).apply();
        mSharedPreferences.edit().putString(SHAREVALUE.user_name, name).apply();
        mSharedPreferences.edit().putString(SHAREVALUE.user_email, email).apply();
        mSharedPreferences.edit().putString(SHAREVALUE.user_mob, mobile).apply();


    }

    public String getHotelname() {
        return mSharedPreferences.getString(SHAREVALUE.hotel_name, "");
    }public String getHotelid() {
        return mSharedPreferences.getString(SHAREVALUE.hotel_id, "");
    }public String getHotelemail() {
        return mSharedPreferences.getString(SHAREVALUE.hotel_email, "");
    }public String getHotelitems() {
        return mSharedPreferences.getString(SHAREVALUE.hotel_items, "");
    }
    public String getUsername() {
        return mSharedPreferences.getString(SHAREVALUE.user_name, "");
    }public String getUserid() {
        return mSharedPreferences.getString(SHAREVALUE.user_id, "");
    }public String getUseremail() {
        return mSharedPreferences.getString(SHAREVALUE.user_email, "");
    }public String getUsermob() {
        return mSharedPreferences.getString(SHAREVALUE.user_mob, "");
    }



    public void setLoginCheck(boolean b) {
        mSharedPreferences.edit().putBoolean(SHAREVALUE.logincheck, b).apply();

    }




    public String getRegType() {


        return mSharedPreferences.getString(SHAREVALUE.regtype, "");


    }

    public boolean getLoginCheck() {


        return mSharedPreferences.getBoolean(SHAREVALUE.logincheck, false);


    }


}
