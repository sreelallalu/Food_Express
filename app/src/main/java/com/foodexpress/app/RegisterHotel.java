package com.foodexpress.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.foodexpress.app.adapter.StudentAdapter;
import com.foodexpress.app.databinding.ActivityRegisterHotelBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterHotel extends BaseActivity {

    private ActivityRegisterHotelBinding binding;
    private List<String> item_list;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_hotel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        binding.itemRecycler.setLayoutManager(mLayoutManager);
        binding.itemRecycler.setItemAnimator(new DefaultItemAnimator());
        item_list = new ArrayList<>();
        adapter = new StudentAdapter(item_list, this);
        binding.itemRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemorder = binding.itemEdit.getText().toString().trim();
                if (itemorder.isEmpty()) {
                    return;
                } else {
                    binding.itemEdit.setText("");
                    item_list.add(itemorder);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;

                final String hotelanme = binding.hotelName.getText().toString();
                String email = binding.emailId.getText().toString();
                String pass = binding.passId.getText().toString();

                if (hotelanme.isEmpty()) {
                    binding.hotelName.setError("Invalid hotel name");
                    check = false;
                }if (pass.isEmpty()) {
                    binding.passId.setError("Invalid password");
                    check = false;
                }
                if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    binding.emailId.setError("Invalid email id");

                    check = false;

                }
                if (item_list.size() == 0) {
                    Toast.makeText(RegisterHotel.this, "Add at least one item", Toast.LENGTH_SHORT).show();
                    check = false;

                }

                if (check) {
                    final ProgressDialog dialog = new ProgressDialog(RegisterHotel.this);
                    dialog.setMessage("Loading...");
                    dialog.show();

                    final JSONArray jsonArray = new JSONArray();
                    try {

                        for (int i = 0; i < item_list.size(); i++) {
                            JSONObject jsonObject = new JSONObject();

                            jsonObject.put("item", item_list.get(i).trim());
                            jsonArray.put(jsonObject);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("hotel_name", hotelanme);
                    hashMap.put("hotel_email", email);
                    hashMap.put("hotel_item", jsonArray.toString());
                    hashMap.put("hotel_pass", pass);

                    RestBuilderPro.getService().hotelreg(hashMap).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();


                                try {
                                    String respo = response.body().string();

                                    JSONObject jsonObject = new JSONObject(respo);
                                    int succ = jsonObject.getInt("success");


                                    if (succ == 1)

                                    {
                                        JSONArray jsonA = jsonObject.getJSONArray("data");
                                        JSONObject jsonObject1 = null;

                                        jsonObject1 = jsonA.getJSONObject(0);

                                        String hotelid = jsonObject1.getString("hotel_id");
                                        String hotelname = jsonObject1.getString("hotel_name");
                                        String hotelemail = jsonObject1.getString("hotel_email");
                                        String hotelitems = jsonObject1.getString("hotel_items");
                                        SharedHelper sharedHelper = new SharedHelper(RegisterHotel.this);
                                        sharedHelper.setHotelDetails(hotelid, hotelname, hotelemail, hotelitems);
                                        sharedHelper.setLoginCheck(true);
                                        sharedHelper.setRegType("hotel");


                                        SnakBarCallback("Success", new CallbackSnak() {
                                            @Override
                                            public void back() {

                                                startActivity(new Intent(RegisterHotel.this, HotelOrders.class));
                                                finish();
                                            }
                                        });

                                    } else {
                                        SnakBar("email id already exists");

                                    }
                                }catch (Exception e){e.printStackTrace();}



                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dialog.dismiss();

                            SnakBar("Server could not connect");

                        }
                    });


                }
            }
        });

    }
}
