package com.example.btvnbuoi2.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btvnbuoi2.Adapter.FurnitureAdapter;
import com.example.btvnbuoi2.DetailActivity;
import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.Model.Utils;
import com.example.btvnbuoi2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    Utils utils;
    public CategoriesFragment() {
        // Required empty public constructor
    }
    public static CategoriesFragment newInstance(int pos) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putInt("category", pos);
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        categoriesFragment.setArguments(bundle);
        return categoriesFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        listView = view.findViewById(R.id.listView);
        //arrayList = utils.getFurnitureFromCategories(bundle.getInt("category"));
        arrayList = utils.getFurnitureFromCategories(bundle.getInt("category"));
        furnitureAdapter = new FurnitureAdapter(getContext(),R.layout.adapter_view_home,arrayList);
        listView.setAdapter(furnitureAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,
                                    long l) {
                utils.addFurintureHistorry(arrayList.get(i));
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("name", arrayList.get(i).getName());
                extras.putString("description", arrayList.get(i).getDescription());
                extras.putParcelable("imagebitmap", arrayList.get(i).getImage2(getContext()));
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
