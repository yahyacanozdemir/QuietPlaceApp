package com.yahyacanozdemir.sessizyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieImageAsset;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class splashScreen extends AppCompatActivity {

    ImageView title ;
    LottieAnimationView map,voice ;

    Animation fromBottom,fromSide,fromUp ;
    int gosterim_suresi = 4700;

    SharedPreferences sharedPreferences ;
    boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("myPrefs",MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTimeData",true);

        if(firstTime){
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    firstTime=false;
                    sharedPreferences.edit().putBoolean("firstTimeData",firstTime).apply();

                    map = (LottieAnimationView) findViewById(R.id.mapjs);
                    voice = (LottieAnimationView) findViewById (R.id.voicejs);
                    title = findViewById(R.id.titleText);

                    fromBottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.frombottom) ;
                    map.setAnimation(fromBottom);
                    fromSide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fromright);
                    title.setAnimation(fromSide);
                    fromUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fromtop);
                    voice.setAnimation(fromUp);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, gosterim_suresi);
        }
        else {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    }


    }

