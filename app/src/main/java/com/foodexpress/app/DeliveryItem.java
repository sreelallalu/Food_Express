package com.foodexpress.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.foodexpress.app.adapter.ItemsAdapter;
import com.foodexpress.app.databinding.ActivityDeliveryItemBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.model.ItemModel;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryItem extends BaseActivity {

    private ActivityDeliveryItemBinding binding;

    private boolean checkradio = true;
    private SharedHelper sharedHelper;
    private List<ItemModel> h_list;
    private String cashmodeName;
    private String cashmodeName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_item);
        sharedHelper = new SharedHelper(this);
        Intent intent = getIntent();
        final String hotel_id = intent.getStringExtra("hotel_id");
        String hotel_item = intent.getStringExtra("hotel_item");
        String hotel_rupee = intent.getStringExtra("hotel_rupee");
        getSpinnerAdapter();


        binding.amountPay.setText(hotel_rupee);
        binding.hRupees.setText(hotel_rupee);
        binding.trinItem.setText(hotel_item);
        binding.hItem.setText(hotel_item);
        binding.homeLinear.setVisibility(View.VISIBLE);
        binding.trainLinear.setVisibility(View.GONE);
        binding.placeoder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == binding.home.getId()) {
                    checkradio = true;
                    binding.homeLinear.setVisibility(View.VISIBLE);
                    binding.trainLinear.setVisibility(View.GONE);
                } else {
                    checkradio = false;
                    binding.homeLinear.setVisibility(View.GONE);
                    binding.trainLinear.setVisibility(View.VISIBLE);

                }
            }
        });

        binding.orderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check1 = true;
                boolean check2 = true;

                HashMap<String, String> hashMap = new HashMap<>();
                if (checkradio) {
                    String address = binding.hAddress.getText().toString().trim();
                    String landmark = binding.hLandmark.getText().toString().trim();
                    String rupees = binding.hRupees.getText().toString().trim();
                    String item = binding.hItem.getText().toString().trim();
                    String mode = binding.hCashMode.getSelectedItem().toString();

                    if (address.isEmpty()) {
                        check1 = false;
                        binding.hAddress.setError("Invalid address");

                    }
                    if (landmark.isEmpty()) {
                        check1 = false;
                        binding.hLandmark.setError("Invalid landmark");

                    }
                    if (rupees.isEmpty()) {
                        check1 = false;
                        binding.hRupees.setError("Invalid rupees");

                    }
                    if (item.isEmpty()) {
                        check1 = false;
                        binding.hItem.setError("Invalid item");

                    }
                    if (check1) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("address", address);
                            jsonObject.put("landmark", landmark);
                            jsonObject.put("rupees", rupees);
                            jsonObject.put("cashmode", cashmodeName1);
                            jsonObject.put("item", item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        hashMap.put("address", jsonObject.toString());
                        hashMap.put("hotel_id", hotel_id);
                        hashMap.put("user_id", sharedHelper.getUserid());
                        hashMap.put("delivery", "home");

                    }


                } else {

                    String train_name = binding.trainName.getText().toString().trim();
                    String train_number = binding.trainNumber.getText().toString().trim();
                    String trian_station = binding.currentStation.getText().toString().trim();
                    String train_delivery = binding.deliveryStation.getText().toString().trim();
                    String coach_no = binding.coachNo.getText().toString().trim();
                    String seat_no = binding.seatNo.getText().toString().trim();
                    String expected_time = binding.expectedTime.getText().toString().trim();
                    String item = binding.trinItem.getText().toString().trim();
                    String rupees = binding.amountPay.getText().toString().trim();
                    String cashmode = binding.tCashMode.getSelectedItem().toString();


                    if (train_name.isEmpty()) {
                        binding.trainName.setError("Invalid train name");
                        check2 = false;
                    }
                    if (train_number.isEmpty()) {
                        binding.trainNumber.setError("Invalid train number");
                        check2 = false;


                    }
                    if (trian_station.isEmpty()) {
                        check2 = false;
                        binding.currentStation.setError("Invalid");

                    }

                    if (train_delivery.isEmpty()) {
                        binding.deliveryStation.setError("Invalid");
                        check2 = false;

                    }
                    if (coach_no.isEmpty()) {
                        check2 = false;

                        binding.coachNo.setError("Invalid");

                    }
                    if (seat_no.isEmpty()) {
                        binding.seatNo.setError("Invalid");
                        check2 = false;

                    }
                    if (expected_time.isEmpty()) {
                        binding.expectedTime.setError("Invalid");
                        check2 = false;

                    }
                    if (item.isEmpty()) {
                        binding.trinItem.setError("Invalid");
                        check2 = false;

                    }
                    if (rupees.isEmpty()) {
                        binding.amountPay.setError("Invalid");
                        check2 = false;
                    }

                    if (check2) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("train_name", train_name);
                            jsonObject.put("train_number", train_number);
                            jsonObject.put("trian_station", trian_station);
                            jsonObject.put("train_delivery", train_delivery);
                            jsonObject.put("coach_no", coach_no);
                            jsonObject.put("seat_no", seat_no);
                            jsonObject.put("expected_time", expected_time);
                            jsonObject.put("item", item);
                            jsonObject.put("rupees", rupees);
                            jsonObject.put("cashmode", cashmodeName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        hashMap.put("address", jsonObject.toString());
                        hashMap.put("hotel_id", hotel_id);
                        hashMap.put("user_id", sharedHelper.getUserid());
                        hashMap.put("delivery", "train");
                    }
                }

                if (check2 && check1) {
                    final ProgressDialog dialog = new ProgressDialog(DeliveryItem.this);
                    dialog.setMessage("Loading...");
                    dialog.show();

                    Log.e("hashmap", String.valueOf(new JSONObject(hashMap)));

                    RestBuilderPro.getService().odering(hashMap).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            dialog.dismiss();
                            if (response.isSuccessful()) {

                                try {
                                    String respo=response.body().string();
                                    Log.e("response",respo);
                                    JSONObject jsonObject=new JSONObject(respo);
                                    int succ=jsonObject.getInt("success");
                                    if(succ==1)
                                    {

                                        SnakBarCallback("Success", new CallbackSnak() {
                                            @Override
                                            public void back() {

                                                finish();
                                            }
                                        });
                                    }else{
                                        SnakBar("Failed");
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dialog.dismiss();
                            Log.e("error",t.getMessage());
                            SnakBar("Failed");

                        }
                    });

                }


            }
        });


    }

    private void getSpinnerAdapter() {

        h_list = new ArrayList<>();
        h_list.add(new ItemModel("Cash","123"));
        h_list.add(new ItemModel("Credit card","123"));
        h_list.add(new ItemModel("Debit card","123"));



        ItemsAdapter  adapter=new ItemsAdapter(DeliveryItem.this, h_list);

        binding.tCashMode.setAdapter(adapter);
        binding.hCashMode.setAdapter(adapter);
        binding.tCashMode.setOnItemSelectedListener(new CashMode());
        binding.hCashMode.setOnItemSelectedListener(new CashMode1());

    }

    private class CashMode implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ItemModel model=h_list.get(position);


            if(model.getName()!="")
            {
                cashmodeName = model.getName();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    private class CashMode1 implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ItemModel model=h_list.get(position);


            if(model.getName()!="")
            {
                cashmodeName1 = model.getName();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
