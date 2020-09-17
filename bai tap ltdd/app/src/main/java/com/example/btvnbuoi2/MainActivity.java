package com.example.btvnbuoi2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.btvnbuoi2.Frament.AccountFragment;
import com.example.btvnbuoi2.Frament.DashboardFragment;
import com.example.btvnbuoi2.Frament.HomeFragment;
import com.example.btvnbuoi2.Frament.NotificationsFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;
    EditText searchView;
    boolean status=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        loadFragment(new AccountFragment());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        searchView = findViewById(R.id.search_vew);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        moveTaskToBack(true);
        finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    getSupportActionBar().setTitle("Dashboard");
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_home:
                    getSupportActionBar().setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_notifications:
                    getSupportActionBar().setTitle("Notification");
                    fragment = new NotificationsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    getSupportActionBar().setTitle("Profile");
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
// load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
