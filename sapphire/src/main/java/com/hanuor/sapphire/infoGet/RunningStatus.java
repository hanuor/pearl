package com.hanuor.sapphire.infoGet;
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
import android.content.Context;
import android.os.Looper;

import java.util.List;

public class RunningStatus extends Thread {
    ActivityManager am = null;
    Context context = null;

    public RunningStatus(Context con){
        context = con;
        am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

    public void run(){
        Looper.prepare();

        while(true){
            // Return a list of the tasks that are currently running,
            // with the most recent being first and older ones after in order.
            // Taken 1 inside getRunningTasks method means want to take only
            // top activity from stack and forgot the olders.
            List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);
            String currentRunningActivityName = taskInfo.get(0).topActivity.getClassName();
            if (currentRunningActivityName.equals("PACKAGE_NAME.ACTIVITY_NAME")) {
                // show your activity here on top of PACKAGE_NAME.ACTIVITY_NAME
            }
        }
    }
}
