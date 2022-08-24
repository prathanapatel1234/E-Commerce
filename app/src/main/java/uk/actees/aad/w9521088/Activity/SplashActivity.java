package uk.actees.aad.w9521088.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;

public class SplashActivity extends AppCompatActivity {

    public static int TIME_OUT = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(new SharedPreferenceData(SplashActivity.this).IsLogin()){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                else{
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }

            }
        },TIME_OUT);
    }
}