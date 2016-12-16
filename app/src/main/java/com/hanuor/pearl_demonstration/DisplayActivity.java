package com.hanuor.pearl_demonstration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    GridView grid;
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

        final ClassicAdapter adapter = new ClassicAdapter(DisplayActivity.this, listofURLs);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(DisplayActivity.this, "Position " + adapterView.getChildAt(i).getTag()   + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
