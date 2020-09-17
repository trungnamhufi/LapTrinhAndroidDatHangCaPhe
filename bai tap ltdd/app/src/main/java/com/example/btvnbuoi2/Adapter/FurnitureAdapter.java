package com.example.btvnbuoi2.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.R;

import java.util.List;

public class FurnitureAdapter extends ArrayAdapter<Furniture> {
    private Context myContext;
    int myLayout;
    List<Furniture> arrayFurnitures;
    public FurnitureAdapter(Context context, int layout, List<Furniture> furnitures)
    {
        super(context,layout,furnitures);
        myContext = context;
        myLayout = layout;
        arrayFurnitures = furnitures;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,parent,false);


        TextView tvName = (TextView) convertView.findViewById(R.id.textViewHome1);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.textViewHome2);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imageHome);



        tvName.setText(arrayFurnitures.get(position).getName());
        tvDescription.setText(arrayFurnitures.get(position).getDescription());
        if(arrayFurnitures.get(position).getImage() == null)
            imgView.setImageBitmap(arrayFurnitures.get(position).getImage2(myContext));
        else
            imgView.setImageBitmap(arrayFurnitures.get(position).getImage());

        return convertView;
    }
}
