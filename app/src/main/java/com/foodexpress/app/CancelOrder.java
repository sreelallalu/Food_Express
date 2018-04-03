package com.foodexpress.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

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

        sharedHelper = new SharedHelper(this);

        getDetails();
    }

    private void getDetails() {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("user_id",sharedHelper.getUserid());
        RestBuilderPro.getService().cancelist(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    try {
                        String resp=response.body().string();
                        JSONObject jsonObject=new JSONObject(resp);
                        int succ=jsonObject.getInt("success");
                        if(succ==1)
                        {
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i = 0; i <jsonArray.length() ; i++) {


                                JSONObject jsOn=jsonArray.getJSONObject(i);
                                String or_id=jsOn.getString("or_id");
                                String or_hotelid=jsOn.getString("or_hotelid");
                                String or_userid=jsOn.getString("or_userid");
                                String or_address=jsOn.getString("or_address");
                                String or_delivery=jsOn.getString("or_delivery");
                                String or_date=jsOn.getString("or_date");

                                List<OrderCancel> list_cancel=new ArrayList<>();
                                list_cancel.add(new OrderCancel(or_id,or_hotelid,or_userid,or_address,or_delivery,or_date));

                            }


                        }else {

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
