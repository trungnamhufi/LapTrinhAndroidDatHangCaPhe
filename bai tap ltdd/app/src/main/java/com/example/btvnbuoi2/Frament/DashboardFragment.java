package com.example.btvnbuoi2.Frament;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.btvnbuoi2.Adapter.CategoriesAdapterGrid;
import com.example.btvnbuoi2.Model.Categories;
import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.Model.Utils;
import com.example.btvnbuoi2.R;
import com.example.btvnbuoi2.SQLite.DBHelper;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    GridView gridView;
    ArrayList<Categories> arrayList;
    CategoriesAdapterGrid furnitureAdapterGrid;
    Utils utils;
    DBHelper dbHelper;

    public DashboardFragment() {
// Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        utils = new Utils(getContext());
        dbHelper = new DBHelper(getContext());
        return inflater.inflate(R.layout.dashboardfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.gridView);
        //arrayList = getMockData();
        if (dbHelper.getALLCategories().size() == 0){
            dbHelper.insertCategories();
        }
        arrayList = dbHelper.getALLCategories();
        furnitureAdapterGrid = new CategoriesAdapterGrid(getContext(), R.layout.adapter_view_dashboard, arrayList);
        gridView.setAdapter(furnitureAdapterGrid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,
                                    long l) {
                FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.nav_host_fragment, CategoriesFragment.newInstance(i)
                );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
