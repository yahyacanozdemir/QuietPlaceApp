package com.yahyacanozdemir.sessizyer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.AudioManager;
import android.widget.Toast;


import com.google.android.gms.location.LocationResult;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MyLocationService extends BroadcastReceiver {


    int choose1,choose2,choose3;
    float lati,longi;

    float lat1,long1;
    float lat2,long2;
    float lat3,long3;

    AudioManager ringtoneMode;
    public static final String ACTION_PROCESS_UPDATE = "com.yahyacanozdemir.sessizyer.UPDATE_LOCATION";
    private static DecimalFormat df = new DecimalFormat("0.00");


    @Override
    public void onReceive(Context context, Intent intent) {
        ringtoneMode = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        choose1 = MainActivity.getInstance().sendMode1();
        choose2 = MainActivity.getInstance().sendMode2();
        choose3 = MainActivity.getInstance().sendMode3();

        lat1=MainActivity.getInstance().sendLat1();
        long1=MainActivity.getInstance().sendLong1();

        lat2=MainActivity.getInstance().sendLat2();
        long2=MainActivity.getInstance().sendLong2();

        lat3=MainActivity.getInstance().sendLat3();
        long3=MainActivity.getInstance().sendLong3();


        if (intent!=null) {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action)){
                LocationResult result = LocationResult.extractResult(intent);

                if (result!=null)
                {
                    Location location =result.getLastLocation();
                    String location_string = new StringBuilder(""+location.getLatitude())
                            .append("///")
                            .append(location.getLongitude())
                            .toString();
                    lati= (float)  location.getLatitude();
                    longi = (float) location.getLongitude();
                    try {

                        if (round(lat1,2) == round(lati,2) && round(long1,2)==round(longi,2)){
                            if(choose1==1) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                Toast.makeText(context,"Your New Ringer Mode Normal! ",Toast.LENGTH_LONG).show();
                            }
                                if(choose1==2) {
                                    ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                    Toast.makeText(context,"Your New Ringer Mode Vibrate! ",Toast.LENGTH_LONG).show();
                                }
                            if(choose1==3) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                Toast.makeText(context,"Your New Ringer Mode Silent! ",Toast.LENGTH_LONG).show();
                            }

                        }

                        if (round(lat2,2) == round(lati,2) && round(long2,2)==round(longi,2)){
                            if(choose2==1) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                Toast.makeText(context,"Your New Ringer Mode Normal! ",Toast.LENGTH_LONG).show();
                            }
                            if(choose2==2) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                Toast.makeText(context,"Your New Ringer Mode Vibrate! ",Toast.LENGTH_LONG).show();
                            }
                            if(choose2==3) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                Toast.makeText(context,"Your New Ringer Mode Silent! ",Toast.LENGTH_LONG).show();
                            }
                        }
                        if (round(lat3,2) == round(lati,2) && round(long3,2)==round(longi,2)){
                            if(choose3==1) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                Toast.makeText(context,"Your New Ringer Mode Normal! ",Toast.LENGTH_LONG).show();
                            }
                            if(choose3==2) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                Toast.makeText(context,"Your New Ringer Mode Vibrate! ",Toast.LENGTH_LONG).show();
                            }
                            if(choose3==3) {
                                ringtoneMode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                Toast.makeText(context,"Your New Ringer Mode Silent! ",Toast.LENGTH_LONG).show();
                            }
                        }

                        MainActivity.getInstance().updateTextView(""+round(long2,2)+"   "+round(lat2,2));

                    }catch (Exception ex)
                    {
                        Toast.makeText(context,"Konum Alınamadı",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}





