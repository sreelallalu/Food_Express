package com.foodexpress.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.foodexpress.app.adapter.OderListAdapter;
import com.foodexpress.app.databinding.ActivityHotelOrdersBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.helper.TimeChange;
import com.foodexpress.app.model.ItemoderModel;
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

public class HotelOrders extends BaseActivity {
    private SharedHelper sharedHelper;
    private List<ItemoderModel> list_orders;
    private ActivityHotelOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_orders);
        sharedHelper = new SharedHelper(this);
        list_orders = new ArrayList<>();

        getOders();

    }

    private void getOders() {

        final ProgressDialog dialog = new ProgressDialog(HotelOrders.this);
        dialog.setMessage("Loading...");
        dialog.show();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hotel_id", sharedHelper.getHotelid());
        RestBuilderPro.getService().order_list(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        String resp = response.body().string();

                        JSONObject jsonObject = new JSONObject(resp);
                        Log.e("response",jsonObject.toString());
                        int succ = jsonObject.getInt("success");
                        if (succ == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonOb = jsonArray.getJSONObject(i);

                                String id = jsonOb.getString("or_id");
                                String hotelid = jsonOb.getString("or_hotelid");
                                String address = jsonOb.getString("or_address");
                                String or_userid = jsonOb.getString("or_userid");
                                String date = jsonOb.getString("or_date");
                                String delivery = jsonOb.getString("or_delivery");


                                  String ItemD="";
                                if(delivery.equalsIgnoreCase("home"))
                                {

                                    JSONObject jsonObject1=new JSONObject(address);
                                    String add=jsonObject1.getString("address");
                                    String landmark=jsonObject1.getString("landmark");
                                    String cashmode=jsonObject1.getString("cashmode");
                                    ItemD=jsonObject1.getString("item");


                                }

                                 if(delivery.equals("train"))
                                 {
                                     JSONObject jsonObject1=new JSONObject(address);
                                     String train_name=jsonObject1.getString("train_name");
                                     String train_number=jsonObject1.getString("train_number");
                                     String trian_station=jsonObject1.getString("trian_station");
                                     String train_delivery=jsonObject1.getString("train_delivery");
                                     String coach_no=jsonObject1.getString("coach_no");
                                     String seat_no=jsonObject1.getString("seat_no");
                                     String expected_time=jsonObject1.getString("expected_time");
                                     ItemD=jsonObject1.getString("item");
                                     String rupees=jsonObject1.getString("rupees");
                                     String cashmode=jsonObject1.getString("cashmode");


                                 }
                                list_orders.add(new ItemoderModel(id, hotelid, or_userid, address, delivery, TimeChange.parseDateToddMMyyyy(date),ItemD));

                            }
                            OderListAdapter oderListAdapter = new OderListAdapter(list_orders, HotelOrders.this);
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(HotelOrders.this));
                            binding.recyclerView.setAdapter(oderListAdapter);
                            oderListAdapter.notifyDataSetChanged();


                        }else{
                            SnakBar("No oders available");
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
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ID = item.getItemId();

        if (ID == R.id.sign_out) {

            sharedHelper.setHotelDetails("", "", "", "");
            sharedHelper.setRegType("");
            sharedHelper.setLoginCheck(false);
            sharedHelper.setUserDetails("", "", "", "");

            startActivity(new Intent(this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
            return true;

        }
        return false;
    }

}
