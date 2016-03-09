package com.quickscore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Softapt on 19/02/2016.
 */
public class SplashActvity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActvity.this,OptionsActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
