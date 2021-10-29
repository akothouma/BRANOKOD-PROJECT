package com.example.newu.branokod3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.newu.branokod3.Varsityitemofinterest.DESCZ1;
import static com.example.newu.branokod3.Varsityitemofinterest.PHOTOWZ1;
import static com.example.newu.branokod3.Varsityitemofinterest.PRICE1;
import static com.example.newu.branokod3.sokoitemofinterest.DESCZ;
import static com.example.newu.branokod3.sokoitemofinterest.EXPEC;
import static com.example.newu.branokod3.sokoitemofinterest.PHOTOWZ;

public class Varsityitemofinterestparse extends AppCompatActivity {

    ImageView imageView;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsityitemofinterestparse);

        imageView= findViewById(R.id.wparseimage);
        t1= findViewById(R.id.wSokodesc);
        t2= findViewById(R.id.wSokoexpe);
        Intent p=getIntent();
        String uri=p.getStringExtra(PHOTOWZ1);
        String desc=p.getStringExtra(DESCZ1);
        String price=p.getStringExtra(PRICE1);

        Picasso.get().load(uri).centerCrop().fit().into(imageView);
        t1.setText(desc);
        t2.setText(price);
    }


}
