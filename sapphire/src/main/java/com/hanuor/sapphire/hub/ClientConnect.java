package com.hanuor.sapphire.hub;
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
import android.widget.ImageView;

import com.hanuor.sapphire.utils.Client;
import com.hanuor.sapphire.utils.DayModuloDeterminer;
import com.hanuor.sapphire.utils.InformationHandler;
import com.hanuor.sapphire.utils.Utility;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
public class ClientConnect {
    private Internals internals;
    private ClientConnect mclient;
    private InformationHandler stickyEvent;
    private static DayModuloDeterminer dayModuloDeterminer;
    public ClientConnect(Context ctx){
        stickyEvent = EventBus.getDefault().getStickyEvent(InformationHandler.class);
        internals = new Internals(ctx);
    }
    public void register(Context ctx, ArrayList<String> tags){
        //the docId will be returned here thanks to the jar file
        //Add relevant conditions here
        //checking jar library
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
//              dayModuloDeterminer.startpvtTreelearning(tags);
            Client mC = new Client(ctx);
            mC.makeJsonString(tags);
        }else{
            Utility.throwRuntimeException();
        }
    }
    public void update(Context ctx, ArrayList<String> tags){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            mclient = new ClientConnect(ctx);
            String docID = internals.readIdfromDevice();
            if(docID==null){
                mclient.register(ctx, tags);
                docID = internals.readIdfromDevice();
            }else{
                internals.updateJsonFollowUp(tags, docID);
                docID = internals.readIdfromDevice();
            }
            Log.d("Sapppdd",""+docID);
            //save Jdoc in the database
        }else{
            Utility.throwRuntimeException();
        }
    }
    public void tagUpdate(String _tag){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            internals.hitTags(_tag);
        }else{
            Utility.throwRuntimeException();
        }
    }
    public void imageStore(Context ctx, ArrayList<ImageView> imgviews){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            internals.storImgs(ctx, imgviews);
        }else{
            Utility.throwRuntimeException();
        }
    }
    public void invokeprivateLearning(String _tag){
        internals.privateHitTag(_tag);
    }
    public void setRecentPrivate(String _tag, ArrayList<String> imageViewArrayList){
        internals.recentTagViewHelper(_tag, imageViewArrayList);
    }

}
