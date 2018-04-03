package com.foodexpress.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.foodexpress.app.databinding.OrderAddressBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderAddress extends BaseActivity {

    private OrderAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_address);
        binding = DataBindingUtil.setContentView(this, R.layout.order_address);
        String sharevalue = getIntent().getStringExtra("address");
        String delivery = getIntent().getStringExtra("or_delivery");





       try {
           String ItemD = "";
           if (delivery.equalsIgnoreCase("home")) {

               JSONObject jsonObject1 = new JSONObject(sharevalue);
               String add = jsonObject1.getString("address");
               String landmark = jsonObject1.getString("landmark");
               String cashmode = jsonObject1.getString("cashmode");
               ItemD = jsonObject1.getString("item");
              String ruppes = jsonObject1.getString("rupees");

               binding.home.setChecked(true);
               binding.homeLinear.setVisibility(View.VISIBLE);
               binding.train.setEnabled(false);

               binding.hAddress.setText(add);
               binding.hLandmark.setText(landmark);
               binding.hCashMode.setText(cashmode);
               binding.hRupees.setText(ruppes);


               binding.hAddress.setEnabled(false);
               binding.hAddress.setClickable(false);

               binding.hLandmark.setEnabled(false);
               binding.hLandmark.setClickable(false);

               binding.hCashMode.setEnabled(false);
               binding.hCashMode.setClickable(false);

               binding.hRupees.setEnabled(false);
               binding.hRupees.setClickable(false);

               binding.hItem.setVisibility(View.GONE);






           }

           if (delivery.equals("train")) {
               JSONObject jsonObject1 = new JSONObject(sharevalue);
               String train_name = jsonObject1.getString("train_name");
               String train_number = jsonObject1.getString("train_number");
               String trian_station = jsonObject1.getString("trian_station");
               String train_delivery = jsonObject1.getString("train_delivery");
               String coach_no = jsonObject1.getString("coach_no");
               String seat_no = jsonObject1.getString("seat_no");
               String expected_time = jsonObject1.getString("expected_time");
               ItemD = jsonObject1.getString("item");
               String rupees = jsonObject1.getString("rupees");
               String cashmode = jsonObject1.getString("cashmode");


               binding.train.setChecked(true);
               binding.trainLinear.setVisibility(View.VISIBLE);
               binding.home.setEnabled(false);


               binding.trainNumber.setText(train_number);
               binding.trainName.setText(train_name);
               binding.currentStation.setText(trian_station);
               binding.deliveryStation.setText(train_delivery);
               binding.coachNo.setText(coach_no);
               binding.seatNo.setText(seat_no);
               binding.expectedTime.setText(expected_time);
               binding.amountPay.setText(rupees);
               binding.tCashMode.setText(cashmode);
               binding.trinItem.setVisibility(View.GONE);


               binding.trainNumber.setEnabled(false);
               binding.trainNumber.setClickable(false);

               binding.trainName.setEnabled(false);
               binding.trainName.setClickable(false);

               binding.currentStation.setEnabled(false);
               binding.currentStation.setClickable(false);

               binding.deliveryStation.setEnabled(false);
               binding.deliveryStation.setClickable(false);


               binding.coachNo.setEnabled(false);
               binding.coachNo.setClickable(false);

               binding.seatNo.setEnabled(false);
               binding.seatNo.setClickable(false);

               binding.expectedTime.setEnabled(false);
               binding.expectedTime.setClickable(false);

               binding.amountPay.setEnabled(false);
               binding.amountPay.setClickable(false);

               binding.tCashMode.setEnabled(false);
               binding.tCashMode.setClickable(false);
           }
       }catch (JSONException e)
       {e.printStackTrace();}
    }
}
