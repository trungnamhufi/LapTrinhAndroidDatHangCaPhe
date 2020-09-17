package com.example.btvnbuoi2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.btvnbuoi2.Adapter.FurnitureAdapter;
import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.Model.Utils;

import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    ArrayList<Furniture> arrayList;
    Utils utils;
    ListView listView;
    FurnitureAdapter furnitureAdapter;
    TagGroup mTagGroup;
    private final static String TAG = "Search";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        utils = new Utils(SearchActivity.this);
        utils.WriteToFileInternal(utils.getMockData());
        arrayList =new ArrayList<>();
        listView = findViewById(R.id.listView);
        furnitureAdapter = new FurnitureAdapter(SearchActivity.this,R.layout.adapter_view_home, arrayList);
        listView.setAdapter(furnitureAdapter);
        //Log.d("FurnitureApp", utils.LoadFileInternal().size()+"");
        searchView = findViewById(R.id.search_vew);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchFurniture(newText);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long
                    l) {

                utils.addFurintureHistorry(arrayList.get(i));
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("name", arrayList.get(i).getName());
                extras.putString("description", arrayList.get(i).getDescription());
                extras.putParcelable("imagebitmap", arrayList.get(i).getImage2(SearchActivity.this));
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        mTagGroup = findViewById(R.id.tag_group);
        mTagGroup.setTags(new String[]{"Bed", "Living",
                "Accessories","Sealy","Christopher"});
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });
    }
    private void searchFurniture(String newText) {
        ArrayList<Furniture> tmp = new ArrayList<>();
        for(Furniture furniture : utils.LoadFileInternal()){
            if(furniture.getName().toLowerCase().contains(newText.toLowerCase())){
                tmp.add(furniture);
            }
        }
        Toast.makeText(this, tmp.size()+"", Toast.LENGTH_SHORT).show();
        if(tmp.size() > 0){
            furnitureAdapter.clear();
            furnitureAdapter.addAll(tmp);
            furnitureAdapter.notifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);
        }
        if(newText.isEmpty()){
            listView.setVisibility(View.GONE);
        }
    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
