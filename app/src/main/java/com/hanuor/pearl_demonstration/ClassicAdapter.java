package com.hanuor.pearl_demonstration;

import android.content.Context;
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
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            Pearl.imageLoader(mContext,Imageid.get(position),imageView,R.drawable.frost);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
