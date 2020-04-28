package com.yahyacanozdemir.sessizyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    permissionsDialog permissionsDialog = new permissionsDialog();
    permissionsDialog2 permissionsDialog2 = new permissionsDialog2();
    permissionsDialog3 permissionsDialog3 = new permissionsDialog3();

    static MainActivity instance ;

    LocationRequest locationRequest ;
    FusedLocationProviderClient fusedLocationProviderClient ;

    public static MainActivity getInstance() {
        return instance;
    }

    TextView txt_location;


    ImageView sound1,sound2,sound3,vibr1,vibr2,vibr3,noSound1,noSound2,noSound3,closeButton1,closeButton2,closeButton3;
    ImageView chooser1,chooser2,chooser3,chooser4,chooser5,chooser6,chooser7,chooser8,chooser9,adress1,adress2,adress3,addAdress1,addAdress2,addAdress3;

    TextView welcomeText,saved1,saved2,saved3;
    TextView adressText1,adressText2,adressText3 ;
    ImageView [] chooserList1,chooserList2,chooserList3 ;

    LottieAnimationView okaybutton,settingsButton ;
    ImageView saveButton ;

    String latestAdress1 ;
    String latestAdress2 ;
    String latestAdress3 ;

    String newWelcomeText ;

    SharedPreferences sharedPreferences1;
    SharedPreferences sharedPreferences2;
    SharedPreferences sharedPreferences3;
    SharedPreferences sharedPreferences4;
    SharedPreferences sharedPreferences5;
    SharedPreferences sharedPreferences6;
    SharedPreferences sharedPreferences7;
    SharedPreferences sharedPreferences8;
    SharedPreferences sharedPreferences9;
    SharedPreferences sharedPreferences10;
    SharedPreferences sharedPreferences11;
    SharedPreferences sharedPreferences12;
    SharedPreferences sharedPreferences13;


    SharedPreferences saverOfAdress1Lat ;
    SharedPreferences saverOfAdress1Long ;

    int soundGroup1Cursiors=0 ;
    int soundGroup2Cursiors=0 ;
    int soundGroup3Cursiors=0 ;
    int isLocationRuleOkay = 0 ;

    float latestAdress1Lat,latestAdress1Long ;
    float latestAdress2Lat,latestAdress2Long ;
    float latestAdress3Lat,latestAdress3Long ;

    AudioManager audioManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this ;
        txt_location= (TextView)findViewById(R.id.textView);

        boolean permissionAccessCoarseLocationApproved =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;

        if (permissionAccessCoarseLocationApproved) {
            boolean backgroundLocationPermissionApproved =
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            == PackageManager.PERMISSION_GRANTED;

            if (backgroundLocationPermissionApproved) {
                updateLocation();
                // App can access location both in the foreground and in the background.
                // Start your service that doesn't have a foreground service type
                // defined.
            } else {
                permissionsDialog.show(getSupportFragmentManager(),"Permission");
                // App can only access location in the foreground. Display a dialog
                // warning the user that your app must have all-the-time access to
                // location in order to function properly. Then, request background
                // location.
                ActivityCompat.requestPermissions(this, new String[] {
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                        0);
            }
        } else {

            permissionsDialog2.show(getSupportFragmentManager(),"permission");
            // App doesn't have access to the device's location at all. Make full request
            // for permission.
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    },
                    1);
        }


        sharedPreferences1 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences2 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences3 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences4 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences5 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences6 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences7 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences8 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences9 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences10 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences11 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences12 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        sharedPreferences13 = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);

        saverOfAdress1Lat = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);
        saverOfAdress1Long = this.getSharedPreferences("com.yahyacanozdemir.sessizyer", Context.MODE_PRIVATE);


        //ses modları tanımlamaları
        sound1 = findViewById(R.id.sound1);
        vibr1 = findViewById(R.id.vibr1);
        noSound1 = findViewById(R.id.quite1);
        sound2 = findViewById(R.id.sound2);
        vibr2 = findViewById(R.id.vibr2);
        noSound2 = findViewById(R.id.quite2);
        sound3 = findViewById(R.id.sound3);
        vibr3 = findViewById(R.id.vibr3);
        noSound3 = findViewById(R.id.quite3);

        //cursior tanımlamaları
        chooser1 = findViewById(R.id.chooser1);
        chooser4 = findViewById(R.id.chooser4);
        chooser7 = findViewById(R.id.chooser7);
        chooser2 = findViewById(R.id.chooser2);
        chooser5 = findViewById(R.id.chooser5);
        chooser8 = findViewById(R.id.chooser8);
        chooser3 = findViewById(R.id.chooser3);
        chooser6 = findViewById(R.id.chooser6);
        chooser9 = findViewById(R.id.chooser9);
        chooserList1 = new ImageView[]{chooser1, chooser2, chooser3};
        chooserList2 = new ImageView[]{chooser4, chooser5, chooser6};
        chooserList3 = new ImageView[]{chooser7, chooser8, chooser9};

        //adres tanımlamaları
        adress1 = findViewById(R.id.Adress1);
        addAdress1 = findViewById(R.id.addAdress1);
        adressText1 = (TextView) findViewById(R.id.adressText1);
        adress2 = findViewById(R.id.Adress2);
        addAdress2 = findViewById(R.id.addAdress2);
        adressText2 = (TextView) findViewById(R.id.adressText2);
        adress3 = findViewById(R.id.Adress3);
        addAdress3 = findViewById(R.id.addAdress3);
        adressText3 = (TextView) findViewById(R.id.adressText3);


        settingsButton = (LottieAnimationView) findViewById(R.id.settingsJson);
        saveButton = (ImageView) findViewById(R.id.saveButton);
        closeButton1=(ImageView)findViewById(R.id.closeButton1);
        closeButton2=(ImageView)findViewById(R.id.closeButton2);
        closeButton3=(ImageView)findViewById(R.id.closeButton3);

        okaybutton = (LottieAnimationView) findViewById(R.id.exitButton);
        //exitButton = findViewById(R.id.exitButton);

        //karşılama yazısı
        welcomeText = findViewById(R.id.welcomeText);

        saved1 = findViewById(R.id.saved1);
        saved2 = findViewById(R.id.saved2);
        saved3 = findViewById(R.id.saved3);


            writeUsername();
            takeUsername();

            writeAdreses();
            takeAdreses();
            checkAdreses();

            writeMods();
            checkCursiors1();
            checkCursiors2();
            checkCursiors3();

            audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);
            modsPermissions();

    }

    //kullanıcı adı işlemleri
    private void writeUsername() {
        Intent mainActvty = getIntent();
        newWelcomeText = mainActvty.getStringExtra("welcomeText");
        if (newWelcomeText != null) {
            welcomeText.setTextSize(35);
            welcomeText.setText(newWelcomeText.toUpperCase());
            saveUsername();
        } else {
            welcomeText.setText("Modify Here From Settings");
           }
    }
    private void saveUsername(){
        sharedPreferences7.edit().putString("savedWelcomeText",newWelcomeText).apply();
    }
    private void takeUsername(){
        newWelcomeText = sharedPreferences7.getString("savedWelcomeText",null);
        if(newWelcomeText!=null)
            welcomeText.setText(newWelcomeText.toUpperCase());
        else {
            writeUsername();
        }
    }

    private void updateLocation() {
        buildLocationRequest();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,getPendingIntent());
    }
    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this,MyLocationService.class);
        intent.setAction(MyLocationService.ACTION_PROCESS_UPDATE);
        return PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }
    public void updateTextView(final String value){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_location.setText("Suanki konumunuz : "+value);
            }
        });
    }


    //Adres İşlemleri
    public void addAdress1(View view) {

        if(internetKontrol()){ //internet kontrol methodu çağırılıyor
            Toast.makeText(getApplicationContext(), "Please long press on your mode address", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Connection Error!", Toast.LENGTH_LONG).show();
        }
        // sharedPreferences1.edit().remove("savedAdress1").apply();
        Intent adress1Map = new Intent(getApplicationContext(),MapsActivitiy.class);
        startActivity(adress1Map);
    }
    public void addAdress2(View view) {

        if(internetKontrol()){ //internet kontrol methodu çağırılıyor
            Toast.makeText(getApplicationContext(), "Please long press on your mode address", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Connection Error!", Toast.LENGTH_LONG).show();
        }
        Intent adress2Map = new Intent(getApplicationContext(),MapsActivity2.class);
        startActivity(adress2Map);


    }
    public void addAdress3(View view) {

        if(internetKontrol()){ //internet kontrol methodu çağırılıyor
            Toast.makeText(getApplicationContext(), "Please long press on your mode address", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Connection Error!", Toast.LENGTH_LONG).show();
        }
        Intent adress3Map = new Intent(getApplicationContext(),MapsActivity3.class);
        startActivity(adress3Map);
    }

    public void writeAdreses(){
        latestAdress1 = sharedPreferences1.getString("savedAdress1",null);
        latestAdress1Lat = sharedPreferences8.getFloat("savedAdress1Lat", 0);
        latestAdress1Long = sharedPreferences9.getFloat("savedAdress1Long", 0);


        latestAdress2 = sharedPreferences2.getString("savedAdress2",null);
        latestAdress2Lat = sharedPreferences10.getFloat("savedAdress2Lat", 0);
        latestAdress2Long = sharedPreferences11.getFloat("savedAdress2Long", 0);

        latestAdress3 = sharedPreferences3.getString("savedAdress3",null);
        latestAdress3Lat = sharedPreferences8.getFloat("savedAdress3Lat", 0);
        latestAdress3Long = sharedPreferences9.getFloat("savedAdress3Long", 0);
    }
    public  void saveAdreses (){

        sharedPreferences1.edit().putString("savedAdress1",latestAdress1).apply();
        sharedPreferences8.edit().putFloat("savedAdress1Lat",latestAdress1Lat).apply();
        sharedPreferences9.edit().putFloat("savedAdress1Long",latestAdress1Long).apply();

        sharedPreferences2.edit().putString("savedAdress2",latestAdress2).apply();
        sharedPreferences10.edit().putFloat("savedAdress2Lat",latestAdress2Lat).apply();
        sharedPreferences11.edit().putFloat("savedAdress2Long",latestAdress2Long).apply();


        sharedPreferences3.edit().putString("savedAdress3",latestAdress3).apply();
        sharedPreferences12.edit().putFloat("savedAdress3Lat",latestAdress3Lat).apply();
        sharedPreferences13.edit().putFloat("savedAdress3Long",latestAdress3Long).apply();

    }
    public void takeAdreses () {
        Intent intent = getIntent();

        if (latestAdress1 == null) {
            latestAdress1 = intent.getStringExtra("userAdress1");
            latestAdress1Lat = intent.getFloatExtra("userAdress1Lat", (float) 0);
            latestAdress1Long = intent.getFloatExtra("userAdress1Long", (float) 0);
            // denemeTexti.setText("Sectiginiz konum : "+adress1Lat+"/"+adress1Long);
            saveAdreses();
        }

        if (latestAdress2 == null) {
            latestAdress2 = intent.getStringExtra("userAdress2");
            latestAdress2Lat = intent.getFloatExtra("userAdress2Lat", (float) 0);
            latestAdress2Long = intent.getFloatExtra("userAdress2Long", (float) 0);
            //denemeTexti.setText("Sectiginiz konum : "+adress2Lat+"/"+adress2Long);
            saveAdreses();
        }

        if (latestAdress3 == null) {
            latestAdress3 = intent.getStringExtra("userAdress3");
            latestAdress3Lat = intent.getFloatExtra("userAdress3Lat", (float) 0);
            latestAdress3Long = intent.getFloatExtra("userAdress3Long", (float) 0);
            //denemeTexti.setText("Sectiginiz konum : "+adress3Lat+"/"+adress3Long);
            saveAdreses();
        }
    }
    public void checkAdreses (){
        if (latestAdress1 != null) {
            addAdress1.setVisibility(View.INVISIBLE);
            adress1.setVisibility(View.VISIBLE);
            closeButton1.setVisibility(View.VISIBLE);
            adressText1.setVisibility(View.VISIBLE);
            adressText1.setText(latestAdress1 + "   ");
            saved1.setText(""+latestAdress1Lat+" /// "+latestAdress1Long);
        }
        if (latestAdress2 != null) {
            addAdress2.setVisibility(View.INVISIBLE);
            adress2.setVisibility(View.VISIBLE);
            closeButton2.setVisibility(View.VISIBLE);
            adressText2.setVisibility(View.VISIBLE);
            adressText2.setText(latestAdress2 + "   ");
            saved2.setText(""+latestAdress2Lat+" /// "+latestAdress2Long);
        }
        if (latestAdress3 != null) {
            addAdress3.setVisibility(View.INVISIBLE);
            adress3.setVisibility(View.VISIBLE);
            closeButton3.setVisibility(View.VISIBLE);
            adressText3.setVisibility(View.VISIBLE);
            adressText3.setText(latestAdress3 + "   ");
            saved3.setText(""+latestAdress3Lat+" /// "+latestAdress3Long);

        }
    }

    public void deleteAdress1 (View view){
        sharedPreferences1.edit().remove("savedAdress1").apply();
        sharedPreferences8.edit().remove("savedAdress1Lat").apply();
        sharedPreferences9.edit().remove("savedAdress1Long").apply();
        sharedPreferences4.edit().remove("savedChoose").apply();
        saved1.setText("Adress1Kaldırıldı!");

        soundGroup1Cursiors=0;
        addAdress1.setVisibility(View.VISIBLE);
        adress1.setVisibility(View.INVISIBLE);
        closeButton1.setVisibility(View.INVISIBLE);
        adressText1.setVisibility(View.INVISIBLE);
        chooser1.setVisibility(View.INVISIBLE);
        chooser2.setVisibility(View.INVISIBLE);
        chooser3.setVisibility(View.INVISIBLE);
    }
    public void deleteAdress2 (View view){
        sharedPreferences2.edit().remove("savedAdress2").apply();
        sharedPreferences10.edit().remove("savedAdress2Lat").apply();
        sharedPreferences11.edit().remove("savedAdress2Long").apply();
        sharedPreferences5.edit().remove("savedChoose2").apply();
        saved2.setText("Adress2Kaldırıldı!");

        soundGroup2Cursiors=0;
        addAdress2.setVisibility(View.VISIBLE);
        adress2.setVisibility(View.INVISIBLE);
        closeButton2.setVisibility(View.INVISIBLE);
        adressText2.setVisibility(View.INVISIBLE);
        chooser4.setVisibility(View.INVISIBLE);
        chooser5.setVisibility(View.INVISIBLE);
        chooser6.setVisibility(View.INVISIBLE);
    }
    public void deleteAdress3 (View view){
        sharedPreferences3.edit().remove("savedAdress3").apply();
        sharedPreferences12.edit().remove("savedAdress3Lat").apply();
        sharedPreferences13.edit().remove("savedAdress3Long").apply();
        sharedPreferences6.edit().remove("savedChoose3").apply();
        saved3.setText("Adress3Kaldırıldı!");

        soundGroup3Cursiors=0;
        addAdress3.setVisibility(View.VISIBLE);
        adress3.setVisibility(View.INVISIBLE);
        closeButton3.setVisibility(View.INVISIBLE);
        adressText3.setVisibility(View.INVISIBLE);
        chooser7.setVisibility(View.INVISIBLE);
        chooser8.setVisibility(View.INVISIBLE);
        chooser9.setVisibility(View.INVISIBLE);
    }



    //Modları kaydedip kaydedilmişleri alma
    public void writeMods (){
        soundGroup1Cursiors = sharedPreferences4.getInt("savedChoose",0);
        soundGroup2Cursiors = sharedPreferences5.getInt("savedChoose2",0);
        soundGroup3Cursiors = sharedPreferences6.getInt("savedChoose3",0);
    }
    public void saveMyMods (View view){
        sharedPreferences4.edit().putInt("savedChoose",soundGroup1Cursiors).apply();
        sharedPreferences5.edit().putInt("savedChoose2",soundGroup2Cursiors).apply();
        sharedPreferences6.edit().putInt("savedChoose3",soundGroup3Cursiors).apply();


       /* Intent intent = new Intent(getApplicationContext(), MyLocationService.class);
        intent.putExtra("userAdress1Lat", adress1Lat);
        intent.putExtra("userAdress1Long", adress1Long);

        intent.putExtra("userAdress2Lat", adress2Lat);
        intent.putExtra("userAdress2Long", adress2Long);

        intent.putExtra("userAdress3Lat", adress3Lat);
        intent.putExtra("userAdress3Long", adress3Long);


        intent.putExtra("group1", soundGroup1Cursiors);
        intent.putExtra("group2", soundGroup2Cursiors);
        intent.putExtra("group3", soundGroup3Cursiors);

        */


    }
    public void modsPermissions(){
        NotificationManager notificationManager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager2.isNotificationPolicyAccessGranted()) {
            permissionsDialog3.show(getSupportFragmentManager(),"Permission");
        }
    }

    //Seçici imleci modlara götürme
    public void snd1(View view) {
       // audioManager.setRingerMode(2);
        if (adress1.isShown()) {
            //  sharedPreferences4.edit().remove("savedChoose").apply();
            soundGroup1Cursiors = 1 ;
            saveMyMods(view);
            soundGroup1();
            //  sharedPreferences4.edit().putInt("savedChoose",soundGroup1Cursiors).apply();
        }
    }
    public void vibr1(View view) {
      //  audioManager.setRingerMode(1);
        if (adress1.isShown() == true) {
            // sharedPreferences4.edit().remove("savedChoose").apply();
            soundGroup1Cursiors = 2 ;
            saveMyMods(view);
            soundGroup1();
            // sharedPreferences4.edit().putInt("savedChoose",soundGroup1Cursiors).apply();
        }
    }
    public void quite1(View view) {
     //   audioManager.setRingerMode(2);
     //   audioManager.setRingerMode(0);
        if (adress1.isShown() == true) {
            //  sharedPreferences4.edit().remove("savedChoose").apply();
            soundGroup1Cursiors = 3 ;
            saveMyMods(view);
            soundGroup1();
            //sharedPreferences4.edit().putInt("savedChoose",soundGroup1Cursiors).apply();
        }
    }
    public void soundGroup1(){
        if(soundGroup1Cursiors==1){
            chooser1.setVisibility(ImageView.VISIBLE);
            chooser2.setVisibility(ImageView.INVISIBLE);
            chooser3.setVisibility(ImageView.INVISIBLE);
        }
        if(soundGroup1Cursiors==2){
            chooser2.setVisibility(ImageView.VISIBLE);
            chooser1.setVisibility(ImageView.INVISIBLE);
            chooser3.setVisibility(ImageView.INVISIBLE);
        }
        if(soundGroup1Cursiors==3){
            chooser3.setVisibility(ImageView.VISIBLE);
            chooser1.setVisibility(ImageView.INVISIBLE);
            chooser2.setVisibility(ImageView.INVISIBLE);
        }
    }
    public void snd2(View view) {
        if (adress2.isShown() == true) {
            //   sharedPreferences5.edit().remove("savedChoose2").apply();
            soundGroup2Cursiors = 1 ;
            saveMyMods(view);
            soundGroup2();
            // sharedPreferences5.edit().putInt("savedChoose2",soundGroup2Cursiors).apply();
        }
    }
    public void vibr2(View view) {
        if (adress2.isShown() == true) {
            //      sharedPreferences5.edit().remove("savedChoose2").apply();
            soundGroup2Cursiors = 2 ;
            saveMyMods(view);
            soundGroup2();
            // sharedPreferences5.edit().putInt("savedChoose2",soundGroup2Cursiors).apply();
        }
    }
    public void quite2(View view) {
        if (adress2.isShown() == true) {
            //   sharedPreferences5.edit().remove("savedChoose2").apply();
            soundGroup2Cursiors = 3 ;
            saveMyMods(view);
            soundGroup2();
            //   sharedPreferences5.edit().putInt("savedChoose2",soundGroup2Cursiors).apply();
        }
    }
    public void soundGroup2(){
        if(soundGroup2Cursiors==1){
            chooser4.setVisibility(ImageView.VISIBLE);
            chooser5.setVisibility(ImageView.INVISIBLE);
            chooser6.setVisibility(ImageView.INVISIBLE);
        }
       if(soundGroup2Cursiors==2){
           chooser5.setVisibility(ImageView.VISIBLE);
           chooser4.setVisibility(ImageView.INVISIBLE);
           chooser6.setVisibility(ImageView.INVISIBLE);
       }
       if(soundGroup2Cursiors==3){
           chooser6.setVisibility(ImageView.VISIBLE);
           chooser4.setVisibility(ImageView.INVISIBLE);
           chooser5.setVisibility(ImageView.INVISIBLE);
       }
   }
    public void snd3(View view) {
        if (adress3.isShown() == true) {
           // sharedPreferences6.edit().remove("savedChoose3").apply();
            soundGroup3Cursiors = 1 ;
            saveMyMods(view);
            soundGroup3();
            //sharedPreferences6.edit().putInt("savedChoose3",soundGroup3Cursiors).apply();
        }
    }
    public void vibr3(View view) {
        if (adress3.isShown() == true) {
            //   sharedPreferences6.edit().remove("savedChoose3").apply();
            soundGroup3Cursiors = 2 ;
            saveMyMods(view);
            soundGroup3();
            //  sharedPreferences6.edit().putInt("savedChoose3",soundGroup3Cursiors).apply();
        }
    }
    public void quite3(View view) {
        if (adress3.isShown() == true) {
            //   sharedPreferences6.edit().remove("savedChoose3").apply();
            soundGroup3Cursiors = 3 ;
            saveMyMods(view);
            soundGroup3();
            //  sharedPreferences6.edit().putInt("savedChoose3",soundGroup3Cursiors).apply();
        }
    }
    public void soundGroup3(){
        if(soundGroup3Cursiors==1){
            chooser7.setVisibility(ImageView.VISIBLE);
            chooser8.setVisibility(ImageView.INVISIBLE);
            chooser9.setVisibility(ImageView.INVISIBLE);
        }
        if(soundGroup3Cursiors==2){
            chooser8.setVisibility(ImageView.VISIBLE);
            chooser7.setVisibility(ImageView.INVISIBLE);
            chooser9.setVisibility(ImageView.INVISIBLE);
        }
        if(soundGroup3Cursiors==3){
            chooser9.setVisibility(ImageView.VISIBLE);
            chooser7.setVisibility(ImageView.INVISIBLE);
            chooser8.setVisibility(ImageView.INVISIBLE);
        }
    }

    //Kaydedilmiş Modları Yerleştirme
    public void checkCursiors1 (){
        if (soundGroup1Cursiors==1){
            chooser1.setVisibility(ImageView.VISIBLE);
            chooser2.setVisibility(ImageView.INVISIBLE);
            chooser3.setVisibility(ImageView.INVISIBLE);

        } else if (soundGroup1Cursiors==2){
            chooser2.setVisibility(ImageView.VISIBLE);
            chooser1.setVisibility(ImageView.INVISIBLE);
            chooser3.setVisibility(ImageView.INVISIBLE);
        } else if (soundGroup1Cursiors==3) {
            chooser3.setVisibility(ImageView.VISIBLE);
            chooser1.setVisibility(ImageView.INVISIBLE);
            chooser2.setVisibility(ImageView.INVISIBLE);
        }else {
            chooser3.setVisibility(ImageView.INVISIBLE);
            chooser1.setVisibility(ImageView.INVISIBLE);
            chooser2.setVisibility(ImageView.INVISIBLE);
        }

    }
    public void checkCursiors2 () {
        if (soundGroup2Cursiors == 1) {
            chooser4.setVisibility(ImageView.VISIBLE);
            chooser5.setVisibility(ImageView.INVISIBLE);
            chooser6.setVisibility(ImageView.INVISIBLE);
        }else if (soundGroup2Cursiors==2){
            chooser5.setVisibility(ImageView.VISIBLE);
            chooser4.setVisibility(ImageView.INVISIBLE);
            chooser6.setVisibility(ImageView.INVISIBLE);
        }else if (soundGroup2Cursiors==3){
            chooser6.setVisibility(ImageView.VISIBLE);
            chooser4.setVisibility(ImageView.INVISIBLE);
            chooser5.setVisibility(ImageView.INVISIBLE);
        }else {
            chooser6.setVisibility(ImageView.INVISIBLE);
            chooser4.setVisibility(ImageView.INVISIBLE);
            chooser5.setVisibility(ImageView.INVISIBLE);
        }
    }
    public void checkCursiors3 (){
        if (soundGroup3Cursiors==1){
            chooser7.setVisibility(ImageView.VISIBLE);
            chooser8.setVisibility(ImageView.INVISIBLE);
            chooser9.setVisibility(ImageView.INVISIBLE);
        }else if (soundGroup3Cursiors==2){
            chooser8.setVisibility(ImageView.VISIBLE);
            chooser7.setVisibility(ImageView.INVISIBLE);
            chooser9.setVisibility(ImageView.INVISIBLE);
        }else if (soundGroup3Cursiors==3){
            chooser9.setVisibility(ImageView.VISIBLE);
            chooser7.setVisibility(ImageView.INVISIBLE);
            chooser8.setVisibility(ImageView.INVISIBLE);
        }else {
            chooser9.setVisibility(ImageView.INVISIBLE);
            chooser7.setVisibility(ImageView.INVISIBLE);
            chooser8.setVisibility(ImageView.INVISIBLE);
        }
    }



    //interneti kontrol eden method
    protected boolean internetKontrol() {
        // TODO Auto-generated method stub
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    public void openSettingsScreen(View view) {
        Intent i = new Intent(getApplicationContext(),settings.class) ;
        startActivity(i);
    }
    public void systemExit(View view){
        saveMyMods(view);
        okaybutton.playAnimation();
        Toast.makeText(getApplicationContext(),"Your Quiet Places Have Been Stored!",Toast.LENGTH_LONG).show();
        //System.exit(1);
    }

    //Adresleri ve modları arkaplan servisine gönderme
    public int sendMode1(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return soundGroup1Cursiors;
    }
    public int sendMode2(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return soundGroup2Cursiors;
    }
    public int sendMode3(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return soundGroup3Cursiors;
    }

    public float sendLat1(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return latestAdress1Lat;
    }
    public float sendLong1(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return latestAdress1Long;
    }

    public float sendLat2(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return latestAdress2Lat;
    }
    public float sendLong2(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return latestAdress2Long;
    }

    public float sendLat3(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return latestAdress3Lat;
    }
    public float sendLong3(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return latestAdress3Long;
    }






}
//Daha Sonrası için Seçilen Adresi Özelleştirme
/* public void saveSpecialAdreses(){
        sharedPreferences14.edit().putString("specialadress1",specialAdress1).apply();
        sharedPreferences15.edit().putString("specialadress2",specialAdress2).apply();
        sharedPreferences16.edit().putString("specialadress3",specialAdress3).apply();
    }*/
/*public void writeSpeacialAdreses(){
        specialAdress1 = sharedPreferences14.getString("savedadress1",null);
        specialAdress2 = sharedPreferences15.getString("savedadress2",null);
        specialAdress3 = sharedPreferences16.getString("savedadress3",null);
    }*/
/*  public void checkSpeacialAdreses(){
        if (specialAdress1 != null) {
            addAdress1.setVisibility(View.INVISIBLE);
            adress1.setVisibility(View.VISIBLE);
            closeButton1.setVisibility(View.VISIBLE);
            adressText1.setVisibility(View.VISIBLE);
            adressText1.setText(specialAdress1);
            saved1.setText(""+latestAdress1Lat+" /// "+latestAdress1Long);
        }
        else if(specialAdress1==null){
            checkAdreses();
        }
        if (specialAdress2 != null) {
            addAdress2.setVisibility(View.INVISIBLE);
            adress2.setVisibility(View.VISIBLE);
            closeButton2.setVisibility(View.VISIBLE);
            adressText2.setVisibility(View.VISIBLE);
            adressText2.setText(specialAdress2);
            saved2.setText(""+latestAdress2Lat+" /// "+latestAdress2Long);
        }
        else if(specialAdress1==null){
            checkAdreses();
        }
        if (specialAdress3 != null) {
            addAdress3.setVisibility(View.INVISIBLE);
            adress3.setVisibility(View.VISIBLE);
            closeButton3.setVisibility(View.VISIBLE);
            adressText3.setVisibility(View.VISIBLE);
            adressText3.setText(specialAdress3);
            saved3.setText(""+latestAdress3Lat+" /// "+latestAdress3Long);
        }
        else if(specialAdress1==null){
            checkAdreses();
        }
    }*/


