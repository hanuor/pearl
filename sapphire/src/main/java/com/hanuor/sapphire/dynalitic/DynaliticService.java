package com.hanuor.sapphire.dynalitic;
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

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hanuor.sapphire.infoGet.TimeStampGS;
import com.hanuor.sapphire.temporarydb.KapacRecentDB;
import com.hanuor.sapphire.temporarydb.StartTimeStoreDB;
import com.hanuor.sapphire.utils.UploadDocs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DynaliticService extends Service implements SensorEventListener {

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private float _sensorMaximumRange;
    private long durationValue;
    private StartTimeStoreDB startTimeStoreDB;
    private KapacRecentDB kapacRecentDB;
    // constant
    public static final long NOTIFY_INTERVAL = 1000; // 10 seconds

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;
    private UploadDocs uploadDocs;
    private long startValue, endValue;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startTimeStoreDB = new StartTimeStoreDB(this);
        uploadDocs = new UploadDocs(this);
        kapacRecentDB  = new KapacRecentDB(this);
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        _sensorMaximumRange = mSensor.getMaximumRange();


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if (_sensorMaximumRange == sensorEvent.values[0]) {
                //near
                long getD = getDuration();
                if (getD == 0) {
                    startValue = System.currentTimeMillis();
                } else {
                    endValue = System.currentTimeMillis();
                    durationValue = startValue - endValue;
                    Log.d("5 minutes", "" + durationValue + "  " + sensorEvent.values[0] + " I am far");
                }
            } else {
                //far
                long getD = getDuration();
                if (getD == 0) {
                    startValue = System.currentTimeMillis();
                } else {
                    endValue = System.currentTimeMillis();
                    durationValue = startValue - endValue;

                    Log.d("5 minutes", "" + durationValue + "  " + sensorEvent.values[0] + " I am far");

                }
                Log.d("5 minutes", "" + sensorEvent.accuracy + "  " + sensorEvent.values[0] + " I am near");

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private long getDuration() {
        if (startValue != 0) {
            return durationValue;
        } else {
            return 0;
        }
    }

    class TimeDisplayTimerTask extends TimerTask {
        boolean check = true;
        TimeStampGS timeStampGS = new TimeStampGS();
        boolean stampCheck = false;
        @Override
        public void run() {
            // run on another thread

            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    Log.d("Summit","Runnit");
                    //uploadDocs.uploadImagetoOnlineDb(getDateandTime());
                   // uploadDocs.uploadTimeStamps(startTimeStoreDB.retrievestartStamp(),startTimeStoreDB.retrieveendStamp());
                    if(getTime().equals("[13:26:11]")){

                        //Do upload
                        if(check){
                            check = false;
                            uploadDocs.uploadTimeStamps(startTimeStoreDB.retrievestartStamp(),startTimeStoreDB.retrieveendStamp());
                        }
                    }
                    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                     List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);
                    String currentRunningActivityName = taskInfo.get(0).topActivity.getPackageName();
                    //Replace package name with dynamic app package name
                    if(currentRunningActivityName.equals(kapacRecentDB.queryPackage())){
                       if(timeStampGS.getSetTime()!=0){

                        }else{
                            timeStampGS.setTime(fetchLongTime());
                       }
                        final long starttime = fetchLongTime();
                       if(starttime == timeStampGS.getSetTime()) {
                           //startTimeStoreDB.clearTimestampTable();
                           check = true;
                           startTimeStoreDB.addstartTimeStampToDB(timeStampGS.getSetTime());
                        }
                    }else{
                        if(check){
                            //startTimeStoreDB.clearendTimeStampTable();
                            final long eendTime = fetchLongTime();
                            timeStampGS.setEndTme(eendTime);
                            check = false;
                            startTimeStoreDB.addEndTimeStampToDB(timeStampGS.getEndTme());
                        }
                    }
                }

            });
        }
        private String getTime(){
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss]");
            return sdf.format(new Date());
        }

        private String getDateandTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }
        private long fetchLongTime(){
            return System.currentTimeMillis();
        }
    }
    private String getDateandTime() {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
        return sdf.format(new Date());
    }
}
