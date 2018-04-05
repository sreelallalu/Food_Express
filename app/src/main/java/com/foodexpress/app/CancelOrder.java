package com.foodexpress.app;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.foodexpress.app.adapter.CancelOdersAdapter;
import com.foodexpress.app.databinding.ActivityCancelOrderBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.model.OrderCancel;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelOrder extends BaseActivity {

    private ActivityCancelOrderBinding binding;
    private SharedHelper sharedHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cancel_order);
        binding.recyclerCancel.setLayoutManager(new LinearLayoutManager(this));
        sharedHelper = new SharedHelper(this);

        getDetails();
    }

    private void getDetails() {


        final  ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", sharedHelper.getUserid());
        RestBuilderPro.getService().cancelist(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               dialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        String resp = response.body().string();
                        JSONObject jsonObject = new JSONObject(resp);
                        int succ = jsonObject.getInt("success");
                        if (succ == 1) {
                            List<OrderCancel> list_cancel = new ArrayList<>();

                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject jsOn = jsonArray.getJSONObject(i);
                                String or_id = jsOn.getString("or_id");
                                String or_hotelid = jsOn.getString("or_hotelid");
                                String or_userid = jsOn.getString("or_userid");
                                String or_address = jsOn.getString("or_address");
                                String or_delivery = jsOn.getString("or_delivery");
                                String or_date = jsOn.getString("or_date");

                                String ItemD="",Rupee="";

                                if (or_delivery.equalsIgnoreCase("home")) {

                                    JSONObject jsonObject1 = new JSONObject(or_address);

                                    ItemD = jsonObject1.getString("item");
                                    Rupee = jsonObject1.getString("rupees");




                                }

                                if (or_delivery.equals("train")) {
                                    JSONObject jsonObject1 = new JSONObject(or_address);

                                    ItemD = jsonObject1.getString("item");
                                    Rupee = jsonObject1.getString("rupees");


                                }


                                    list_cancel.add(new OrderCancel(or_id,ItemD,Rupee , or_date));

                            }
                            CancelOdersAdapter adapter = new CancelOdersAdapter(list_cancel, CancelOrder.this);
                            binding.recyclerCancel.setAdapter(adapter);

                        } else {
                            SnakBar("no more data");

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();

                SnakBar("Failed");
            }
        });
    }

    public void cancelItem(String position)
    {
        final  ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("order_id", position);
        RestBuilderPro.getService().canceorder(hashMap).enqueue(new Callback<ResponseBody>() {
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
                SnakBar("Failed");

            }
        });
    }
}
