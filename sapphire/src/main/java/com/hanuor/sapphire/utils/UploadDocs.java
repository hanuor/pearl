package com.hanuor.sapphire.utils;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.hanuor.container.LibraryDatabase;
import com.hanuor.sapphire.infoGet.BatteryStatus;
import com.hanuor.sapphire.temporarydb.KapacRecentDB;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.temporarydb.UploadDateStoreDb;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UploadDocs extends Activity{
    private Context context;
    private BatteryStatus batteryStatus;
    private SapphireImgDbHelper sapphireImgDbHelper;
    private UploadDateStoreDb uploadDateStoreDb;
    private KapacRecentDB kapacRecentDB;
    @TargetApi(Build.VERSION_CODES.FROYO)
    public UploadDocs(Context ctx){
        this.context = ctx;
        batteryStatus = new BatteryStatus();
        uploadDateStoreDb = new UploadDateStoreDb(context);
        sapphireImgDbHelper = new SapphireImgDbHelper(context);
        kapacRecentDB = new KapacRecentDB(context);
        Backendless.initApp( context, "ECDF6288-9FD1-56B8-FFB7-A7E5A4228A00", "C0C1CB99-9130-88FC-FFA5-C98526E98100", "v1" );
    }


    public void uploadTimeStamps(String _startTimeStamp, String _endTimeStamp){
        context.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        byte[] _startTimeStampBytes;
        byte[] _endTimeStampBytes;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            _startTimeStampBytes = _startTimeStamp.getBytes(StandardCharsets.UTF_8); // Java 7+ only
            _endTimeStampBytes = _endTimeStamp.getBytes(StandardCharsets.UTF_8); // Java 7+ only
        }else{
            _startTimeStampBytes = _startTimeStamp.getBytes(Charset.forName("UTF-8"));
            _endTimeStampBytes = _endTimeStamp.getBytes(Charset.forName("UTF-8"));
        }


        if(batteryStatus.isBatteryStatus() && batteryStatus.getBatteryPercentage() > 20){
            Backendless.Files.saveFile(kapacRecentDB.queryPackage() + LibraryDatabase.STARTTIMESTAMPPATH, LibraryDatabase.STARTTIMESTAMPFORMAT, _startTimeStampBytes, true, new AsyncCallback<String>() {
            @Override
            public void handleResponse(String s) {
                Log.d("Insamareen",""+s);

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.d("Insamareen",backendlessFault.toString());
            }
            });

            Backendless.Files.saveFile(kapacRecentDB.queryPackage() + LibraryDatabase.ENDTIMESTAMPPATH, LibraryDatabase.ENDTIMESTAMPFORMAT, _endTimeStampBytes, true, new AsyncCallback<String>() {
                @Override
                public void handleResponse(String s) {
                    Log.d("Insamareen",""+s);

                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    Log.d("Insamareen",backendlessFault.toString());
                }
            });
        }
       }
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryStatus.setBatteryStatus(isPhonePluggedIn(context));
            batteryStatus.setBatteryPercentage(level);
            Log.d("Grouplove",""+batteryStatus.isBatteryStatus() + " And " + batteryStatus.getBatteryPercentage());
           }
    };

    @Override
    protected void onPause() {
        super.onPause();
        context.unregisterReceiver(mBatInfoReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(mBatInfoReceiver);
    }
    public static boolean isPhonePluggedIn(Context context){
        boolean charging = false;

        final Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean batteryCharge = status==BatteryManager.BATTERY_STATUS_CHARGING;

        int chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        if (batteryCharge) charging=true;
        if (usbCharge) charging=true;
        if (acCharge) charging=true;

        return charging;
    }

    public int printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        int days = (int) elapsedDays;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return days;
    }

public void uploadImagetoOnlineDb(String _currentDate){
    context.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    ArrayList<String> _tags = sapphireImgDbHelper.getAllTags();
    for(String eleTags : _tags){
        byte[] b_Array = sapphireImgDbHelper.imgquery(eleTags);
        Backendless.Files.saveFile(kapacRecentDB.queryPackage()+"/img_data", eleTags + ".png", b_Array, true, new AsyncCallback<String>() {
            @Override
            public void handleResponse(String s) {
                Log.d("VamHan",""+s);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.d("VamHan",""+backendlessFault.getMessage());
            }
        });
    }
    if(uploadDateStoreDb.retrieveValue() == null){
        //new Data
        uploadDateStoreDb.insertNewDate(_currentDate);
    }else{

        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
        try {
            Date Pdate = sdf.parse(uploadDateStoreDb.retrieveValue());
            Date Cdate = sdf.parse(_currentDate);
            int getDif = printDifference(Pdate, Cdate);
            if(getDif == 3){
                if(batteryStatus.isBatteryStatus() && batteryStatus.getBatteryPercentage() > 20) {
                    Log.d("NakedandFam","Approved");
                    uploadDateStoreDb.clearTable();
                    uploadDateStoreDb.insertNewDate(_currentDate);

                }

                }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

}
