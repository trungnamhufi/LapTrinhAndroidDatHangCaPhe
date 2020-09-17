package com.example.btvnbuoi2.Frament;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.btvnbuoi2.InfoActivity;
import com.example.btvnbuoi2.LoginActivity;
import com.example.btvnbuoi2.MainActivity;
import com.example.btvnbuoi2.R;

import java.nio.file.Files;

public class AccountFragment extends Fragment {
    MenuItem menuItem;
    EditText edtName, edtEmail, edtPass, edtUsername;
    Button btnLogout, btnLogin;
    RadioGroup rdbGroup;
    FragmentManager fr;
    public AccountFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_info, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        edtName = view.findViewById(R.id.editText4);
        edtEmail = view.findViewById(R.id.editText);
        edtUsername = view.findViewById(R.id.editText2);
        edtPass = view.findViewById(R.id.editText3);
        rdbGroup = view.findViewById(R.id.radioGroup);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogin = view.findViewById(R.id.btnLogin);

        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        edtName.setText(sharedPreferences.getString("name","admin"));
        edtUsername.setText(sharedPreferences.getString("username","admin"));
        edtPass.setText(sharedPreferences.getString("password","123"));
        edtEmail.setText(sharedPreferences.getString("email","admin@gmail.com"));
        rdbGroup.check(sharedPreferences.getInt("sex",R.id.radiobutton1));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.putExtra("username",edtUsername.getText().toString());
                intent.putExtra("password",edtPass.getText().toString());
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    /*public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater)
    {
        menuInflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuSave:

        }
        return false;
    }*/
}
