package com.hanuor.sapphire.hub;/*
 * Copyright (C) 2016 Hanuor Inc.
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
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanuor.sapphire.R;
import com.hanuor.sapphire.infoGet.StartEngineModulePrimary;
import com.hanuor.sapphire.temporarydb.HintsStoreDB;
import com.hanuor.sapphire.temporarydb.SapphireImgDbHelper;
import com.hanuor.sapphire.temporarydb.SuggestionTempDBHandler;
import com.hanuor.sapphire.utils.RandomUtility;

import java.util.ArrayList;


public class SuggestionView extends RelativeLayout{
    private TextView textView;
    private String defaultheaderTextColor = "#eeeeee";
    private String defaultfooterTextColor = "#eeeeee";
    private HintsStoreDB hintsStoreDB;
    private float footerTextSize = 13f;
    private transient Context context;
    private float headerTextSize = 13f;
    String TEXT = "Suggestionbox";
    String Ftext = "Default";
    ImageView imageView;
    TextView valueTextView, footer, headerText;
    ImageView minusButton;
    TextView tv2;
    private SapphireImgDbHelper sapphireImgDbHelper;
    private StartEngineModulePrimary startEngineModulePrimary;
    SuggestionTempDBHandler suggestionTempDBHandler ;
    View rootView;
    public SuggestionView(Context con) {
        super(con);
        sapphireImgDbHelper = new SapphireImgDbHelper(context);
        this.context = con;
        textView = new TextView(context);
        tv2 = new TextView(context);
        imageView = new ImageView(context);
    }

    public SuggestionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Calling constructor to initialize values and Classes.

        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        sapphireImgDbHelper = new SapphireImgDbHelper(context);
        textView = new TextView(context, attrs);
        tv2 = new TextView(context);
        imageView = new ImageView(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.suggestionbox,0,0);

        try {
            String text = typedArray.getString(R.styleable.suggestionbox_headerText);
            String headerTextColor = typedArray.getString(R.styleable.suggestionbox_headertextColor);
            String background_color = typedArray.getString(R.styleable.suggestionbox_backgroundColor);
            float headerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_headerTextFontSize, headerTextSize);
            float footerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_footertextSize, footerTextSize);
            String footerTextColor = typedArray.getString(R.styleable.suggestionbox_footertextColor);
            String footerText = typedArray.getString(R.styleable.suggestionbox_footerText);

            if(footerText!=null){
                Ftext = footerText;
            }

            if(footerTextColor!=null){
                defaultfooterTextColor = footerTextColor;
            }

            if(footerTextFontSize!=0){
                footerTextSize = footerTextFontSize;
            }

            if (text!=null)
                TEXT = text;
            if (headerTextColor!=null)
                defaultheaderTextColor = headerTextColor;

            if (headerTextFontSize!=0)
                headerTextSize = headerTextFontSize;
            //setUPSuggestion(context, null, 0);

        } finally {
            typedArray.recycle();
        }


    }


    public SuggestionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("SuperME","2");
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        sapphireImgDbHelper = new SapphireImgDbHelper(context);
        textView = new TextView(context, attrs, defStyleAttr);
        imageView = new ImageView(context, attrs, defStyleAttr);

        tv2 = new TextView(context);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.suggestionbox,0,0);

        try {

            String text = typedArray.getString(R.styleable.suggestionbox_headerText);
            String headerTextColor = typedArray.getString(R.styleable.suggestionbox_headertextColor);
            String background_color = typedArray.getString(R.styleable.suggestionbox_backgroundColor);
            float headerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_headerTextFontSize, headerTextSize);
            String footerTextColor = typedArray.getString(R.styleable.suggestionbox_footertextColor);
            float footerTextFontSize = typedArray.getFloat(R.styleable.suggestionbox_footertextSize, footerTextSize);
            String footerText = typedArray.getString(R.styleable.suggestionbox_footerText);

            if(footerText!=null){
                Ftext = footerText;
            }

            if(footerTextColor!=null){
                defaultfooterTextColor = footerTextColor;
            }

            if(footerTextFontSize!=0){
                footerTextSize = footerTextFontSize;
            }

            if (text!=null)
                TEXT = text;
            if (headerTextColor!=null)
                defaultheaderTextColor = headerTextColor;
           if (headerTextFontSize!=0)
                headerTextSize = headerTextFontSize;


            setUPSuggestion(context, null,1);

        } finally {
            typedArray.recycle();
        }


        //setUPSuggestion(context, null,0);
    }

    public void setUPSuggestion(final Context context, Bitmap bitmp, int resId) {

        if(resId == 0){
         case0(context,bitmp);
        }else{
            case1(context);
        }
    }

    private void case1(Context context){
        hintsStoreDB = new HintsStoreDB(context);
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        rootView = inflate(context, R.layout.singleheaderview, this);

        headerText = (TextView) rootView.findViewById(R.id.headerText);

        RelativeLayout.LayoutParams paramsT = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsT.addRule(RelativeLayout.CENTER_HORIZONTAL, headerText.getId());
        paramsT.setMargins(21,22,22,22);
        headerText.setLayoutParams(paramsT);

        headerText.setGravity(Gravity.LEFT);
        headerText.setTextSize(headerTextSize);
        headerText.setTextColor(Color.parseColor(defaultheaderTextColor));
        headerText.setClickable(false);
        ArrayList<String> recString = new ArrayList<String>();
       recString = hintsStoreDB.query();
        int no = RandomUtility.getRandomValue(recString.size()-1, 0);
        headerText.setText(recString.get(no));
    }
    private void case0(Context context,Bitmap bitmp){
        suggestionTempDBHandler = new SuggestionTempDBHandler(context);
        rootView = inflate(context, R.layout.sapphireview, this);
        valueTextView = (TextView) rootView.findViewById(R.id.header);
        footer = (TextView) rootView.findViewById(R.id.footer);
        RelativeLayout.LayoutParams paramsT = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsT.addRule(RelativeLayout.CENTER_HORIZONTAL, valueTextView.getId());
        paramsT.setMargins(21,22,22,22);
        valueTextView.setLayoutParams(paramsT);
        valueTextView.setText(TEXT);
        valueTextView.setGravity(Gravity.CENTER);
        valueTextView.setTextSize(headerTextSize);
        valueTextView.setTextColor(Color.parseColor(defaultheaderTextColor));
        valueTextView.setClickable(false);
        minusButton = (ImageView) rootView.findViewById(R.id.imageBack);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, minusButton.getId());
        params.addRule(RelativeLayout.BELOW, valueTextView.getId());
        minusButton.setLayoutParams(params);
        minusButton.setClickable(false);
        if(bitmp!= null){
            minusButton.setImageBitmap(bitmp);
        }else{
            //Try to fetch image from the backend. Try the internet connection . Use internet for thart.

          /*  String urlpic = "https://api.backendless.com/ECDF6288-9FD1-56B8-FFB7-A7E5A4228A00/v1" +
                    "/files/SapphireDemo*57f3f577e4b0b14082481f27/girl*57f3f577e4b0b14082481f27.jpg";
*/
        }
        RelativeLayout.LayoutParams paramsF = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsF.addRule(RelativeLayout.CENTER_HORIZONTAL, footer.getId());
        paramsF.addRule(RelativeLayout.BELOW, minusButton.getId());
        paramsF.setMargins(3,5,3,5);
        footer.setLayoutParams(paramsF);
        footer.setTextColor(Color.parseColor(defaultfooterTextColor));
        footer.setText(Ftext);
        footer.setTextSize(footerTextSize);
        footer.setClickable(false);
    }

    public void setanimation(Animation animation){
        this.setAnimation(animation);
    }
    public void show(){
        this.setVisibility(View.VISIBLE);
    }
    public void dismiss(){
        this.setVisibility(View.GONE);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);}
    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
    }
    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
    }
    public void startanimation(Animation animation){
        this.startAnimation(animation);
    }
    public void cancelanimation(Animation animation){
        this.cancelanimation(animation);
    }
    public void tagEventActionListener(){
    }


}
