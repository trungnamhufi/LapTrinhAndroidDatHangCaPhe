package com.example.btvnbuoi2;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin, btnRegister, btnOK, btnCancel;
    private final int REQUEST_CODE = 101;
    String username, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.editText);
        edtPassword = findViewById(R.id.editText1);

        Intent intent = getIntent();
        edtUsername.setText(intent.getStringExtra("username"));
        edtPassword.setText(intent.getStringExtra("password"));

        final SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "admin");
        pass = sharedPreferences.getString("password","123");

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUsername.getText().toString().isEmpty() ||
                        edtPassword.getText().toString().isEmpty()){
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.dialog_custom);
                    btnOK = dialog.findViewById(R.id.btnOK);
                    btnCancel = dialog.findViewById(R.id.btnCancel);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View view) {
                            Intent intent = new Intent(LoginActivity.this,
                                    RegisterActivity.class);
                            startActivityForResult(intent, 100);

                            dialog.dismiss();

                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View view) {
                            moveTaskToBack(true);
                            finish();
                        }
                    });
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }else if(edtPassword.getText().toString().length() < 3){
                    edtPassword.setError("Minimum 3 number");
                }else if(edtPassword.getText().toString().length() > 8){
                    edtPassword.setError("Maximum 8 number");
                }else{
                    if(edtUsername.getText().toString().equals(username)&& edtPassword.getText().toString().equals(pass)) {
                        /*Intent intent = new Intent(LoginActivity.this,
                                InfoActivity.class);
                        intent.putExtra("username", edtUsername.getText().toString());
                        intent.putExtra("password", edtPassword.getText().toString());
                        startActivityForResult(intent, REQUEST_CODE);*/
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("tt", 1);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Accout không tồn tại !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                data.putExtra("Username", edtUsername.getText().toString());
                data.putExtra("Password", edtPassword.getText().toString());
                startActivityForResult(data, REQUEST_CODE);
            }
        });*/
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        moveTaskToBack(true);

        finish();
    }
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            //String user = data.getExtras().getString("Username", edtUsername.getText().toString());
            //String password = data.getExtras().getString("Password", edtPassword.getText().toString());
            String user = data.getStringExtra("Username");
            String pass = data.getStringExtra("Password");
            edtUsername.setText(user.toString());
            edtPassword.setText(pass.toString());
        }
    }*/
}
