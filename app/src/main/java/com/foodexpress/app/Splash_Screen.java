package com.foodexpress.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.foodexpress.app.helper.SharedHelper;

public class Splash_Screen extends BaseActivity {

    private String regType;
    private boolean loginCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash__screen);
        TextView textView=(TextView)findViewById(R.id.slashtxt) ;
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Indy_Women.ttf");
        textView.setTypeface(typeface);

        SharedHelper sharedHelper=new SharedHelper(this);
        loginCheck = sharedHelper.getLoginCheck();
        regType = sharedHelper.getRegType();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!loginCheck)
                {startActivity(new Intent(Splash_Screen.this,LoginActivity.class));}
                else
                {
                    if(regType.equalsIgnoreCase("hotel"))
                    {
                        startActivity(new Intent(Splash_Screen.this,HotelOrders.class));
                    }else{
                        startActivity(new Intent(Splash_Screen.this,UserDashBoard.class));
                    }

                }
                finish();
            }
        },2000);
    }
}
