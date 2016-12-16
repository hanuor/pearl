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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hanuor.sapphire.infoGet.StartEngineModulePrimary;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.utils.ExceptionHandler;
import com.hanuor.sapphire.utils.ImagesUtil;
import com.hanuor.sapphire.utils.InformationHandler;
import com.hanuor.sapphire.utils.Utility;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
/*
There needs to be an imageview or some kind if we want to display it in the suggestion view box.
To do this we must have to pass the list of view objects and their intents/actions.
This is the main task that we need to do.
*/
public class SapphireApi {
    private Context context = null;
    private SapphireApi connect = null;
    private ArrayList<String> tags = null;
    private ArrayList<String> updateTags = null;
    private View mview = null;
    private ArrayList<String> listofTags = null;
    private String mgainTag = null;
    private ArrayList<ImageView> imageViews = null;
    private EventBus bus = EventBus.getDefault();
    private ClientConnect mclient;
    private InformationHandler stickyEvent;
    private boolean individualmode = false;
    private ArrayList<String> tagslist;
    private ArrayList<Button> buttonArrayList = null;
    private StartEngineModulePrimary startEngineModulePrimary;
    public SapphireApi(Context context){
        stickyEvent = EventBus.getDefault().getStickyEvent(InformationHandler.class);
        if(context == null){
            ExceptionHandler.writeError("Context cannot be null");
        }else{
             if(stickyEvent != null) {
                Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
                this.context = context;
                mclient = new ClientConnect(context);
            }else{
                Utility.throwRuntimeException();
            }
        }
    }
  public void setInstance(SapphireApi connect){
        this.connect = connect;
    }
    public SapphireApi registerTags(ArrayList<String> tags){
        //these are the security checks
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.tags = tags;
            mclient.register(context, tags);

            return connect;
           }else{
            Utility.throwRuntimeException();
            return null;
        }

    }
    public SapphireApi addTags(ArrayList<String> tags){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.updateTags = tags;
            mclient.update(context, tags);
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
    }
    public SapphireApi enableIndividualModelling(boolean value){
        connect.individualmode = value;
        if(connect.individualmode) {
            //Enable the private tree to true
            if (connect.imageViews != null || connect.buttonArrayList != null) {
                //Call the client heirarchy
                //mclient.startLearning(connect.imageViews, connect.buttonArrayList);
            } else {
                Utility.throwRuntimeException(" Have you registered the views yet? ");
            }
        }else{
            //Only enable the public tree
       }
        return connect;
    }
    public SapphireApi gain(Object tag){

        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            Log.d("Sapphire]",""+tag.toString().length());
            //connect.mgainTag = (String) tag;
           /* if(intentObject != null){
                //This means that the user wants to generate the ML concept
              //  check(suggestionView, intentObject);

            }*/
            mclient.tagUpdate((String) tag);

            // mclient.invokeprivateLearning((String) tag);

            //check(suggestionView);
            //mclient.setRecentPrivate((String) tag, connect.tagslist);
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
    }
    public SapphireApi registerImageViews(ArrayList<ImageView> Views){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            ArrayList<String> tags = new ArrayList<String>();
            for(int i = 0; i < Views.size(); i++){
                tags.add((String)Views.get(i).getTag());
            }
            connect.tagslist = tags;
            connect.imageViews = Views;

            mclient.imageStore(context, Views);

            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
     }
    public SapphireApi registerButtons(ArrayList<Button> buttons){
        if(stickyEvent != null) {
            Log.d("Sticky bus event"," " + stickyEvent.getKEYID() + " "+stickyEvent.getKEYSECRET()+" "+stickyEvent.getVALIDATOR());
            connect.buttonArrayList = buttons;
            return connect;
        }else{
            Utility.throwRuntimeException();
            return null;
        }
    }

    //Delete this method. This is just for testing

    public void setRandomMeasures(boolean value, SuggestionView suggestionView){
        if(value){
            startEngineModulePrimary = new StartEngineModulePrimary(context, suggestionView);
            startEngineModulePrimary.startSuggestions(true);
        }

    }
    public void check(SuggestionView suggestionView){
        Log.d("SapphireAccess","true0");
        SapphireImgDbHelper sapphireImgDbHelper = new SapphireImgDbHelper(context);
        ImagesUtil imagesUtil = new ImagesUtil();
        Log.d("SapphireAccess","true1");
        suggestionView.setUPSuggestion(context, imagesUtil.byteToBitmap(sapphireImgDbHelper.imgquery("girl")), 1);
    }

}


