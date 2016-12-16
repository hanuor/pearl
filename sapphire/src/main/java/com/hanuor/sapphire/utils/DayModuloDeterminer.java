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

import android.content.Context;

import com.hanuor.sapphire.temporarydb.PrivateDatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class DayModuloDeterminer {
    private final Context context;

    public DayModuloDeterminer(Context context) {
        this.context = context;
    }

    public void startpvtTreelearning(ArrayList<String> _tags){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        PrivateDatabaseHelper privateDatabaseHelper = new PrivateDatabaseHelper(context);



    }
}
