package com.hanuor.pearl_demonstration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hanuor.pearl.Pearl;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 03-08-2016.
 */
public class ClassicAdapter extends BaseAdapter{
    private Context mContext;
    private final ArrayList<String> Imageid;


    public ClassicAdapter(Context c,ArrayList<String> Imageid ) {
        mContext = c;
        this.Imageid = Imageid;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        Log.d("Paral", ""+ Imageid.size());
        return Imageid.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.single, null);
            ImageView thumb= (ImageView)grid.findViewById(R.id.grid_image);

            Log.d("ferrrari","HEY");
            thumb.setTag(position);
            Log.d("fferhh",thumb.getTag()+"");
            Log.d("fffffff",""+position);

            if(position == (getCount()-1)){
                Log.d("Weate","We are here");
            }
            Pearl.imageLoader(mContext,Imageid.get(position),thumb,R.drawable.more);

        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
