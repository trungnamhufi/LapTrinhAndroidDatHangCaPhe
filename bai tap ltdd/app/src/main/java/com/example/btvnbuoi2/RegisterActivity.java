package com.example.btvnbuoi2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail, edtUsername, edtPassword, edtConfirm;
    Button btnSignIn, btnCancel;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Khởi tạo SharedPreferences.
        sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_register);
        edtEmail = findViewById(R.id.editText);
        edtUsername = findViewById(R.id.editText2);
        edtPassword = findViewById(R.id.editText3);
        edtConfirm = findViewById(R.id.editText4);
        btnSignIn = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 moveTaskToBack(true);finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isValid(edtEmail.getText().toString())){
                    edtEmail.setError("Invalid Email Address");
                    return;
                }
                if(edtUsername.getText().toString().isEmpty()){
                    edtUsername.setError("Username cannot be null ");
                    return;
                }
                if(edtPassword.getText().toString().isEmpty()){
                    edtPassword.setError("Password required");
                    return;
                }
                if(edtConfirm.getText().toString().isEmpty()){
                    edtConfirm.setError("Password required");
                    return;
                }
                if(edtPassword.getText().toString().length() > 8){
                    edtPassword.setError("Maximum 8 number");
                    return;
                }else if(edtPassword.getText().toString().equals(edtConfirm.getText().toString()))
                {
                    //Tạo dữ cho SharedPreferences.
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",edtUsername.getText().toString());
                    editor.putString("password",edtPassword.getText().toString());
                    editor.putString("email",edtEmail.getText().toString());
                    editor.putString("name","");
                    editor.putInt("sex",0);
                    editor.commit();
                    /*Intent intent = new Intent(RegisterActivity.this,
                            InfoActivity.class);
                    intent.putExtra("username", edtUsername.getText().toString());
                    intent.putExtra("password",edtPassword.getText().toString());
                            intent.putExtra("email",edtEmail.getText().toString());
                    startActivity(intent);
                    finish();*/
                    Intent intent = new Intent(RegisterActivity.this,InfoActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    edtPassword.setError("Password and confirm password does not match");
                            edtConfirm.setText("");
                    return;
                }
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        moveTaskToBack(true);


        finish();
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
