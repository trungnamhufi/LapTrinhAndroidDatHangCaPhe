package com.example.btvnbuoi2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btvnbuoi2.Model.Furniture;

public class DetailActivity extends AppCompatActivity {
    TextView editText1,editText2;
    ImageView imageView;
    Furniture furniture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editText1 = findViewById(R.id.editTextDetail1);
        editText2 = findViewById(R.id.editTextDetail2);
        imageView = findViewById(R.id.imageDetail);
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        String name = (String) extras.getString("name");
        String description = (String) extras.getString("description");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name) ;
        actionBar.setDisplayHomeAsUpEnabled(true);
        editText1.setText("Description");
        editText2.setText(description);
        imageView.setImageBitmap(bmp);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}
