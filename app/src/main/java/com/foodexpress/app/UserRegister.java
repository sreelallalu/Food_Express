package com.foodexpress.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.foodexpress.app.databinding.ActivityUserRegisterBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegister extends BaseActivity {

    private ActivityUserRegisterBinding binding;
    private SharedHelper sharedHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        sharedHelper = new SharedHelper(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_register);
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.username.getText().toString().trim();
                String email = binding.email.getText().toString().trim();
                String mob = binding.mobile.getText().toString().trim();
                String pass = binding.pass.getText().toString().trim();
                boolean check = true;
                if (name.isEmpty()) {

                    check=false;
                    binding.username.setError("Invalid name");
                }
                if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    check=false;
                    binding.email.setError("Invalid email");

                }
                if (mob.isEmpty()) {

                    check=false;
                    binding.mobile.setError("Invalid mobile");


                }
                if (pass.isEmpty()) {

                    check=false;
                    binding.pass.setError("Invalid email");

                }

                if(check)
                {final ProgressDialog dialog = new ProgressDialog(UserRegister.this);
                    dialog.setMessage("Loading..");
                    dialog.show();
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("user_name",name);
                    hashMap.put("user_mobile",mob);
                    hashMap.put("user_password",pass);
                    hashMap.put("user_email",email);
                    RestBuilderPro.getService().userreg(hashMap).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            dialog.dismiss();

                            if(response.isSuccessful()) {


                                try {

                                    String respo = response.body().string();

                                    JSONObject jsonObject = new JSONObject(respo);
                                    int succ = jsonObject.getInt("success");


                                    if (succ == 1)

                                    {
                                        JSONArray jsonA = jsonObject.getJSONArray("data");
                                        JSONObject jsonObject1 = null;

                                        jsonObject1 = jsonA.getJSONObject(0);
                                        String user_id = jsonObject1.getString("user_id");
                                        String user_name = jsonObject1.getString("user_name");
                                        String user_email = jsonObject1.getString("user_email");
                                        String user_mobile = jsonObject1.getString("user_mobile");
                                        sharedHelper.setUserDetails(user_id, user_name, user_email, user_mobile);
                                        sharedHelper.setLoginCheck(true);
                                        sharedHelper.setRegType("user");


                                        SnakBarCallback("Success", new CallbackSnak() {
                                            @Override
                                            public void back() {
                                                startActivity(new Intent(UserRegister.this, UserDashBoard.class));
                                                finish();
                                            }
                                        });
                                    } else {
                                        SnakBar("email id already exists");

                                    }


                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }



                                }







                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dialog.dismiss();
                            SnakBar("Server not responding");
                            Log.e("error",t.getMessage());
                        }
                    });
                }
            }
        });


    }
}
