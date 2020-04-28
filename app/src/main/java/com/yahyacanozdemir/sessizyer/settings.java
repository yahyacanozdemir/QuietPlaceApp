package com.yahyacanozdemir.sessizyer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class settings extends AppCompatActivity {

    ImageView settingsText,backButton,settingsIcon,saveButton ;
    TextView userNameGive,lcTV,modeTV ;
    EditText takeUserName ;
    String userName="Dear";
    String welcomeText ;
    SharedPreferences sharedPreferences1;
    LottieAnimationView saveButtonJson,lcPermissionLt,modePermissionLT ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences1 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);


        settingsText = findViewById(R.id.settingsText);
        settingsIcon = findViewById(R.id.settingsIcon);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);
        userNameGive = findViewById(R.id.userNameTextView);
        lcTV= findViewById(R.id.goLocationPermissionTextView);
        takeUserName = findViewById(R.id.usernameEditText);
        saveButtonJson = findViewById(R.id.saveJson);
        lcPermissionLt = findViewById(R.id.locationPermissionJson);
        modePermissionLT= findViewById(R.id.modePermissionJson);


        takeSettings();

            welcomeText = "Welcome "+userName;
    }

    public void goLCsettings(View view){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package","com.yahyacanozdemir.sessizyer", null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Permissions > Location > Allow All The Time", Toast.LENGTH_LONG).show();
    }
    public void goModesettings(View view){
        startActivity(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
        Toast.makeText(getApplicationContext(), "Quite Place > Allow Do Not Distrub > Turn On > Allow", Toast.LENGTH_LONG).show();
    }

    public void saveSettings(View view) {
        if(takeUserName.getText().toString().isEmpty()==false)
            userName= String.valueOf(takeUserName.getText());
        sharedPreferences1.edit().putString("savedUsername",userName).apply();
        welcomeText = "Welcome "+userName+"!";

        saveButtonJson.setVisibility(View.VISIBLE);
        saveButtonJson.playAnimation();
    }
    private void takeSettings() {
        saveButtonJson.setVisibility(View.INVISIBLE);
        userName = sharedPreferences1.getString("savedUsername","Dear");

    }
    public void backtoMain(View view) {
        Intent mainActvty = new Intent(getApplicationContext(),MainActivity.class);
        mainActvty.putExtra("welcomeText", welcomeText);
        startActivity(mainActvty);
    }
}
