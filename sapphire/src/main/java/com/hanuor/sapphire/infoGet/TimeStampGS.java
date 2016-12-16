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

public class TimeStampGS {
    long stTime;
    long endTme;

    public long getSetTime() {
        return stTime;
    }
    public void setTime(long _time){
        this.stTime = _time;
    }
    public void setEndTme(long _time){
        this.endTme = _time;
    }
    public long getEndTme(){
        return endTme;
    }
}
