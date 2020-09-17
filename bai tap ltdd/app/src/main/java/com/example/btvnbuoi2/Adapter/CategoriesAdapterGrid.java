package com.example.btvnbuoi2.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btvnbuoi2.Model.Categories;
import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapterGrid extends ArrayAdapter<Categories> {
    private Context myContext;
    int myLayout;
    List<Categories> arrayCategories;
    public CategoriesAdapterGrid(Context context, int layout, List<Categories> categories )
    {
        super(context,layout,categories);
        myContext = context;
        myLayout = layout;
        arrayCategories = categories;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,parent,false);


        TextView tvName = (TextView) convertView.findViewById(R.id.textViewDashboard);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imageViewDashboard);

        tvName.setText(arrayCategories.get(position).getName());
        if(arrayCategories.get(position).getImage() == null)
            imgView.setImageBitmap(arrayCategories.get(position).getImage2(myContext));
        else
            imgView.setImageBitmap(arrayCategories.get(position).getImage());
        return convertView;
    }
}
