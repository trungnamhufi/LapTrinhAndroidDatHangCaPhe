package com.example.btvnbuoi2.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.btvnbuoi2.Model.Categories;
import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.Model.Utils;
import com.example.btvnbuoi2.R;

import java.util.ArrayList;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    private  static  final  String dbName = "FurnitureDB.db";
    String tblFurniture = "tblFurniture";
    String tblCategories = "tbtCategories";
    public DBHelper(Context context)  {
        super(context, dbName, null, 1);
        this.context = context;
    }
    String sqlFurniture= "CREATE TABLE IF NOT EXISTS tblFurniture (" +
            " ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " Name TEXT," +
            " Image TEXT," +
            " Description TEXT," +
            " CategoriesID INTEGER );";
    String sqlCategories = "CREATE TABLE IF NOT EXISTS tbtCategories (" +
            " CategoriesID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " Name TEXT," +
            " Image TEXT );";
        @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlFurniture);
        db.execSQL(sqlCategories);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void ThanhCong()
    {
        Toast.makeText(context, "Thanh Cong", Toast.LENGTH_LONG).show();
    }
    public ArrayList<Categories> getMockDataCategories(){
        ArrayList<Categories> tmp = new ArrayList<>();
        tmp.add(new Categories("BedRoom", "bed_room.jpg"));
        tmp.add(new Categories("LivingRoom", "living_room.jpg"));
        tmp.add(new Categories("MeetingRoom", "meeting_room.jpg"));
        tmp.add(new Categories("Accessories", "accessories.jpg"));
        return tmp;
    }
    public void insertCategories(){
        ArrayList<Categories> arrCa = getMockDataCategories();
        SQLiteDatabase db = this.getWritableDatabase();
        for(Categories ca : arrCa) {
            ContentValues cv = new ContentValues();
            cv.put("Name", ca.getName());
            cv.put("Image", ca.getImage2());
            db.insert("tbtCategories", null, cv);
        }
        db.close();
    }
    public ArrayList<Furniture> getMockData(){
        ArrayList<Furniture> tmp = new ArrayList<>();
        tmp.add(new Furniture(context.getString(R.string.name_product_one),
                context.getString(R.string.product_one),"product1.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_tow),
                context.getString(R.string.product_tow), "product2.jpg"));
        tmp.add(new Furniture(context.getString(R.string.name_product_three),
                context.getString(R.string.product_three), "product3.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_four),
                context.getString(R.string.product_four), "product4.png"));
        tmp.add(new Furniture(context.getString(R.string.name_product_five),
                context.getString(R.string.product_five), "product5.jpg"));
        return tmp;
    }
    public void insertFurniture(){
        ArrayList<Furniture> arrFurniture = getMockData();
        SQLiteDatabase db = this.getWritableDatabase();
        Random random = new Random();
            for(Furniture fu : arrFurniture) {
            ContentValues cv = new ContentValues();
            cv.put("Name", fu.getName());
            cv.put("Image", fu.getImage2());
            cv.put("Description", fu.getDescription());
            cv.put("CategoriesID",random.nextInt(4) + 1 );
            db.insert("tblFurniture", null, cv);
        }
        db.close();
    }
    public ArrayList<Furniture> getALLFurniture() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Furniture> arr = new ArrayList<>();
        String sql = "select * from tblFurniture";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String image = csr.getString(2);
                    String description = csr.getString(3);
                    int categoriesID = csr.getInt(4);
                    arr.add(new
                            Furniture(name,description,image,categoriesID,id));
                } while (csr.moveToNext());
            }
        }
        db.close();
        return arr;
    }
    public ArrayList<Categories> getALLCategories(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Categories> arr = new ArrayList<>();
        String sql = "select * from tbtCategories";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String image2 = csr.getString(2);
                    arr.add(new Categories(name, image2, id));
                } while (csr.moveToNext());
            }
        }
        db.close();
        return arr;
    }
    /*

    public Categories findByCatetgoriesID(int id){
        ArrayList<Categories> arr = getALLCategories();
        for(Categories ca : arr){
            if(ca.getId() == id){
                return ca;
            }
        }
        return null;
    }
    public Categories addFurnitureToCategories(int categoriesId){
        Categories categories = findByCatetgoriesID(categoriesId);
        ArrayList<Furniture> arrFurniture = getALLFurniture();
        for(Furniture furniture : arrFurniture){
            if(furniture.getCategoriesID() == categoriesId){
                categories.getArrayList().add(furniture);
            }
        }
        return categories;
    }
    */
}
