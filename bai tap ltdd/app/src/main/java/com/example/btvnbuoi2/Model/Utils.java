package com.example.btvnbuoi2.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.btvnbuoi2.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<Furniture> furnitureHistory = new ArrayList<>();
    public void addFurintureHistorry(Furniture furniture) {
        int i = -1;
            i = furnitureHistory.indexOf(furniture);
            if(i == -1) {
                this.furnitureHistory.add(furniture);
            }
    }
    public ArrayList<Furniture> getFurnitureHistory()
    { return this.furnitureHistory; }

    Context context;
    public Utils(Context context) {
        this.context = context;
    }
    static String filename="database";

    public void WriteToFileInternal(ArrayList<Furniture> arrayList){
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new
                    ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Furniture> LoadFileInternal(){
        ArrayList<Furniture> arrayList = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<Furniture>) objectInputStream.readObject();
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Furniture> getMockData(){
        ArrayList<Furniture> tmp = new ArrayList<>();
        tmp.add(new Furniture(context.getString(R.string.name_product_one),
                context.getString(R.string.product_one),"product1.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_tow),
                context.getString(R.string.product_tow), "product2.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_three),
                context.getString(R.string.product_three), "product3.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_four),
                context.getString(R.string.product_four), "product4.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_five),
                context.getString(R.string.product_five), "product5.png"));
        return tmp;
    }
    public Bitmap convertStringToBitmapFromAccess(String filename){
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
    public ArrayList<Categories> getMockDataCategories(){
        ArrayList<Categories> tmp = new ArrayList<>();
        tmp.add(new Categories("BedRoom", "bed_room.jpg"));
        tmp.add(new Categories("LivingRoom", "living_room.jpg"));
        tmp.add(new Categories("MeetingRoom", "meeting_room.jpg"));
        tmp.add(new Categories("Accessories", "accessories.jpg"));
        return tmp;
    }
    public ArrayList<Furniture> getFurnitureFromCategories(int pos){
        ArrayList<Furniture> tmp = new ArrayList<>();
        switch (pos){
            case 0:
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_one),
                                context.getString(R.string.product_one), "product1.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_tow),
                        context.getString(R.string.product_tow), "product2.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_three),
                        context.getString(R.string.product_three), "product3.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_four),
                        context.getString(R.string.product_four), "product4.png"));
                break;
            case 1:
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_three),
                        context.getString(R.string.product_three), "product3.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_four),
                        context.getString(R.string.product_four),"product4.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_five),
                        context.getString(R.string.product_five), "product2.png"));
                break;
            case 2:
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_tow),
                        context.getString(R.string.product_tow),"product2.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_three),
                        context.getString(R.string.product_three),"product3.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_four),
                        context.getString(R.string.product_four),"product4.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_five),
                        context.getString(R.string.product_five), "product5.png"));
                break;
            case 3:
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_three),
                        context.getString(R.string.product_three), "product3.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_four),
                        context.getString(R.string.product_four), "product4.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_five),
                        context.getString(R.string.product_five), "product5.png"));
                tmp.add(new
                        Furniture(context.getString(R.string.name_product_one),
                        context.getString(R.string.product_one), "product1.png"));
                break;
        }
        return tmp;
    }

}
