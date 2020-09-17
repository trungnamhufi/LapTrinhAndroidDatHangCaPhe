package com.example.btvnbuoi2.Frament;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.btvnbuoi2.Adapter.FurnitureAdapter;
import com.example.btvnbuoi2.Model.Furniture;
import com.example.btvnbuoi2.R;
import com.example.btvnbuoi2.Model.Utils;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    ListView listView2;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    private static final String TAG = "vcl";
    public NotificationsFragment() {
// Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.notificationsfragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        listView2 = view.findViewById(R.id.listView2);

        Utils utils = new Utils(getContext());
        arrayList = utils.getFurnitureHistory();
        furnitureAdapter = new FurnitureAdapter(getContext(),R.layout.adapter_view_home,arrayList);
        listView2.setAdapter(furnitureAdapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.furnitureHistory.add(arrayList.get(i));
            }
        });
    }
}
