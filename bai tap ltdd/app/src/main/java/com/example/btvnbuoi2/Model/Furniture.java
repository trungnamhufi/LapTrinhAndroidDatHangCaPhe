package com.example.btvnbuoi2.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class Furniture implements Serializable {
    private int id;
    String name;
    String description;
    Bitmap image;
    private int CategoriesID;
    private String image2;
    Context context;

    public Furniture(Context context) {
        this.context = context;
    }
    public Furniture(String name, String description, Bitmap image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
    public Furniture(String name, String description, String image2) {
        this.name = name;
        this.description = description;
        this.image2 = image2;
    }
    public Furniture(String name, String description, String image2,int CategoriesID, int id) {
        this.name = name;
        this.description = description;
        this.image2 = image2;
        this.CategoriesID = CategoriesID;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public String getImage2()
    {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public int getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        CategoriesID = categoriesID;
    }
}
