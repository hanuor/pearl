package com.hanuor.sapphire;
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

import android.content.Context;
import android.util.Log;

import com.hanuor.sapphire.hub.SapphireApi;
import com.hanuor.sapphire.temporarydb.KapacRecentDB;
import com.hanuor.sapphire.utils.InformationHandler;
import com.hanuor.sapphire.utils.RuntimeHandler;
import com.hanuor.sapphire.utils.Utility;

import de.greenrobot.event.EventBus;

public class Sapphire {

    private static String questappkey;
    private static RuntimeHandler runtimeHandler;
    private static EventBus bus = EventBus.getDefault();
    private static KapacRecentDB kapacRecentDB;
    public Sapphire(){

    }
    volatile static SapphireApi mconnect =  null;
    public static SapphireApi with(Context context){
        kapacRecentDB = new KapacRecentDB(context);
        mconnect = new SapphireApi(context);
        mconnect.setInstance(mconnect);
        kapacRecentDB.setTablePackageStorage(context.getPackageName());
        Log.d("NUCELA",""+context.getPackageName());
        return mconnect;
    }
    public static boolean initialize(Context context, String appKeyID, String keySecret){
        Utility.throwExceptionIfNullOrBlank(context, "context");
        Utility.throwExceptionIfNullOrBlank(appKeyID, "appKey");
        runtimeHandler = new RuntimeHandler();
        runtimeHandler.setKey_ID(appKeyID);
        runtimeHandler.setKey_secret(keySecret);
        runtimeHandler.setaBoolean("true");

        bus.postSticky(new InformationHandler(runtimeHandler.getKey_ID(), runtimeHandler.getKey_secret(), runtimeHandler.getaBoolean()));
        questappkey = appKeyID;
        //check if key matches to the key stored in Database
        //if else statement

        if(questappkey.equalsIgnoreCase("hanuor")){
            return true;
     }else{
            return false;
        }
    }
}
