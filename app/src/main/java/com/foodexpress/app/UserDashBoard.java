package com.foodexpress.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.foodexpress.app.adapter.HotelListAdapter;
import com.foodexpress.app.adapter.ItemsAdapter;
import com.foodexpress.app.databinding.UserDashBoardBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.model.HotelModel;
import com.foodexpress.app.model.ItemModel;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDashBoard extends BaseActivity {

    private SharedHelper sharedHelper;
    private List<HotelModel> list_hotel;
    private UserDashBoardBinding binding;
    private HotelListAdapter hotelListAdapter;
    private List<ItemModel> list_items;
    private String hotel_id;
    private String rupeesTemp;
    private String hotel_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_dash_board);
        binding = DataBindingUtil.setContentView(this, R.layout.user_dash_board);

        sharedHelper = new SharedHelper(this);
        list_hotel = new ArrayList<>();

        list_items = new ArrayList<>();

        getItem();

        binding.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.itemsSpinner.getSelectedItem() != null) {

                    if (hotel_item.isEmpty()) {
                        return;
                    }
                    Intent intent = new Intent(UserDashBoard.this, DeliveryItem.class);
                    intent.putExtra("hotel_id", hotel_id);
                    intent.putExtra("hotel_item", hotel_item);
                    intent.putExtra("hotel_rupee", rupeesTemp);
                    startActivity(intent);
                }
            }
        });


    }

    private void getItem() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();

        RestBuilderPro.getService().hotellist().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                if (response.isSuccessful())

                {
                    try {
                        String respo = response.body().string();
                        JSONObject jsonObject = new JSONObject(respo);
                        int succ = jsonObject.getInt("success");
                        if (succ == 1) {
                            list_hotel.clear();
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonO = jsonArray.getJSONObject(i);

                                String name = jsonO.getString("hotel_name");
                                String id = jsonO.getString("hotel_id");
                                String items = jsonO.getString("hotel_items");
                                String email = jsonO.getString("hotel_email");
                                list_hotel.add(new HotelModel(name, email, items, id));

                            }
                            hotelListAdapter = new HotelListAdapter(UserDashBoard.this, list_hotel);
                            binding.hotelSpinner.setAdapter(hotelListAdapter);

                            binding.hotelSpinner.setOnItemSelectedListener(new OnItemClick());


                        } else {


                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();

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

        if(ID==R.id.cancel)
        {
            startActivity(new Intent(UserDashBoard.this,CancelOrder.class));
            return true;
        }
        return false;
    }

    private class OnItemClick implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            HotelModel model = list_hotel.get(position);

            list_items.clear();
            if (model.getName() != "") {
                hotel_id = model.getId();



                try {
                    JSONArray jsonArray = new JSONArray(model.getItems());
                    for (int i = 0; i < jsonArray.length(); i++) {


                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("item");
                        String rupee = jsonObject.getString("rupee");

                        list_items.add(new ItemModel(name,rupee));

                    }
                    ItemsAdapter itemsAdapter = new ItemsAdapter(UserDashBoard.this, list_items);
                    binding.itemsSpinner.setAdapter(itemsAdapter);
                    binding.itemsSpinner.setOnItemSelectedListener(new ItemSpinner());



                } catch (JSONException e) {


                }

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class ItemSpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ItemModel model=list_items.get(position);
            if(model.getName()!="")
            {

                rupeesTemp = model.getRupees();
                hotel_item = model.getName();
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
