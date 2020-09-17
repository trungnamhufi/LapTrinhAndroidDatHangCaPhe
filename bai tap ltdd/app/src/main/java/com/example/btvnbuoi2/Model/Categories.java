package com.example.btvnbuoi2.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Categories {
    private int id;
    String name;
    private String image2;
    ArrayList<Furniture> arrayList;
    Bitmap image;
    public Categories(String name, ArrayList<Furniture> arrayList) {
        this.name = name;
        this.arrayList = arrayList;
    }
    public Categories(String name, String image2,int id)
    {
        this.name = name;
        this.setImage2(image2);
        this.id = id;
    }
    public Categories(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }
    public Categories(String name, String image2) {
        this.name = name;
        this.image2 = image2;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Furniture> getArrayList() {
        return arrayList;
    }
    public void setArrayList(ArrayList<Furniture> arrayList) {
        this.arrayList = arrayList;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
    public static Bitmap convertStringToBitmapFromAccess(Context context, String
            filename){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage2(Context context) {
        return convertStringToBitmapFromAccess(context,image2);
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    public String getImage2()
    {
        return image2;
    }
}
