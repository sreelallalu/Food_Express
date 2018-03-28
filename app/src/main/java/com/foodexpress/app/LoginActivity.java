package com.foodexpress.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.foodexpress.app.databinding.ActivityLoginBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private boolean usertype = true;
    private boolean checklogin;
    private SharedHelper sharedHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.user.setChecked(true);

        sharedHelper = new SharedHelper(this);
        checklogin = sharedHelper.getLoginCheck();


        binding.logincheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.user.getId() == checkedId) {
                    usertype = true;
                } else {
                    usertype = false;

                }
            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usertype) {
                    startActivity(new Intent(LoginActivity.this, RegisterHotel.class));


                } else {
                    startActivity(new Intent(LoginActivity.this, UserRegister.class));

                }
            }
        });
        binding.loginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = binding.loginid.getText().toString().trim();
                String Password = binding.loginpass.getText().toString().trim();
                boolean check = true;
                if (UserName.isEmpty()) {
                    check = false;
                    binding.loginid.setError("Invalid LoginId");

                }
                if (Password.isEmpty()) {
                    check = false;
                    binding.loginid.setError("Invalid password");

                }

                if (check) {

                    final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                    dialog.setMessage("Loading..");
                    dialog.show();

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("username", UserName);
                    hashMap.put("password", Password);
                    hashMap.put("type", usertype ? "user" : "hotel");

                    RestBuilderPro.getService().login(hashMap).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            dialog.dismiss();
                            if (response.isSuccessful()) {


                               try {
                                   String respo = response.body().string();

                                   JSONObject jsonObject = new JSONObject(respo);
                                   int succ = jsonObject.getInt("success");


                                   if (succ == 1)

                                   {


                                       JSONArray jsonA = jsonObject.getJSONArray("data");
                                       JSONObject jsonObject1 = null;

                                       jsonObject1 = jsonA.getJSONObject(0);
                                       if (!usertype) {

                                           String hotelid = jsonObject1.getString("hotel_id");
                                           String hotelname = jsonObject1.getString("hotel_name");
                                           String hotelemail = jsonObject1.getString("hotel_email");
                                           String hotelitems = jsonObject1.getString("hotel_items");

                                           sharedHelper.setHotelDetails(hotelid, hotelname, hotelemail, hotelitems);

                                       } else {

                                           String user_id = jsonObject1.getString("user_id");
                                           String user_name = jsonObject1.getString("user_name");
                                           String user_email = jsonObject1.getString("user_email");
                                           String user_mobile = jsonObject1.getString("user_mobile");
                                           sharedHelper.setUserDetails(user_id, user_name, user_email, user_mobile);
                                       }
                                       sharedHelper.setLoginCheck(true);
                                       sharedHelper.setRegType(usertype ? "user" : "hotel");
                                       SnakBarCallback("Success", new CallbackSnak() {
                                           @Override
                                           public void back() {
                                               if (!usertype) {
                                                   startActivity(new Intent(LoginActivity.this, HotelOrders.class));

                                               } else {
                                                   startActivity(new Intent(LoginActivity.this, UserDashBoard.class));

                                               }

                                               finish();
                                           }
                                       });


                                   } else {
                                       SnakBar("check user credentials");


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
