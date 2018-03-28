package com.foodexpress.app;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.foodexpress.app.databinding.ActivityLoginBinding;
import com.foodexpress.app.helper.SharedHelper;
import com.foodexpress.app.model.AddSuccess;
import com.foodexpress.app.service.RestBuilderPro;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
                if(!usertype)
                {
                    startActivity(new Intent(LoginActivity.this,RegisterHotel.class));


                }else {
                    startActivity(new Intent(LoginActivity.this,UserRegister.class));

                }
            }
        });
        binding.loginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName=binding.loginid.getText().toString().trim();
                String Password=binding.loginpass.getText().toString().trim();
                boolean check=true;
                if(UserName.isEmpty())
                {
                    check=false;
                    binding.loginid.setError("Invalid LoginId");

                }
                if(Password.isEmpty())
                {
                    check=false;
                    binding.loginid.setError("Invalid password");

                }

                if(check)
                {
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("username",UserName);
                    hashMap.put("password",Password);
                    hashMap.put("type",usertype?"user":"hotel");

                    RestBuilderPro.getService().login(hashMap).enqueue(new Callback<AddSuccess>() {
                        @Override
                        public void onResponse(Call<AddSuccess> call, Response<AddSuccess> response) {

                            if(response.isSuccessful())
                            {
                                AddSuccess data=response.body();
                                if(data.getSuccess()==1)
                                {
                                   String value =data.getData();

                                    try {
                                        JSONObject jsonObject=new JSONObject(value);

                                        sharedHelper.setLoginCheck(true);
                                        sharedHelper.setRegType(usertype?"user":"hotel");



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }else{
                                    SnakBar("check user credentials");


                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<AddSuccess> call, Throwable t) {
                            SnakBar("Server could not connect");

                        }
                    });



                }
            }
        });
    }
}
