package com.hanuor.pearl_demonstration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    GridView grid;
    int img[] = {R.drawable.q,R.drawable.e,R.drawable.r,R.drawable.w,R.drawable.t,R.drawable.y,R.drawable.u,R.drawable.i,R.drawable.o,R.drawable.f};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ArrayList<String> listofURLs= new ArrayList<String>();
         listofURLs = getIntent().getStringArrayListExtra("list");

        //Used for testing purpose. It works
      //  Object ret = Pearl.retrieveJsonObject("0tag");
        //Toast.makeText(DisplayActivity.this, ""+ret.toString(), Toast.LENGTH_SHORT).show();
        //Log.d("Checksum",""+ret.toString());

        ClassicAdapter adapter = new ClassicAdapter(DisplayActivity.this, listofURLs);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
    }
}
