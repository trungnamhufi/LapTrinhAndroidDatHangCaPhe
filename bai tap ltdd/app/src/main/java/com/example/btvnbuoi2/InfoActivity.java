package com.example.btvnbuoi2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class InfoActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPass, edtUsername;
    Button btnLogout,btnLogin;
    RadioGroup rdbGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        edtName = findViewById(R.id.editText4);
        edtEmail = findViewById(R.id.editText);
        edtUsername = findViewById(R.id.editText2);
        edtPass = findViewById(R.id.editText3);
        rdbGroup = findViewById(R.id.radioGroup);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogout.setVisibility(View.INVISIBLE);
        btnLogin.setVisibility(View.INVISIBLE);
        getSupportActionBar().setTitle("Account Info");
        /*
        Intent intent = getIntent();
        edtEmail.setText(intent.getStringExtra("email"));
        edtUsername.setText(intent.getStringExtra("username"));
        edtPass.setText(intent.getStringExtra("password"));*/

        final SharedPreferences sharedPreferences = getSharedPreferences("account",MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("username",""));
        edtPass.setText(sharedPreferences.getString("password",""));
        edtEmail.setText(sharedPreferences.getString("email",""));
    }
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        moveTaskToBack(true);

        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuSave){

            SharedPreferences sharedPreferences = getSharedPreferences("account",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",edtName.getText().toString());
            editor.putInt("sex",rdbGroup.getCheckedRadioButtonId());
            editor.commit();

            Intent intent = new Intent(InfoActivity.this,
                    LoginActivity.class);
            intent.putExtra("username",edtUsername.getText().toString());
            intent.putExtra("password",edtPass.getText().toString());
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
