package com.example.newu.branokod3;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.P;

public class sharemyitems extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharemyitems);


        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/images");
        String shareBody = "Check out the items and services I have posted in my BRANOKOD account";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody );
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"http://branokoduserauthentication.firebaseio.com/Soko Uploads");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://branokoduserauthentication.firebaseio.com/Varsity Uploads"+"http://branokoduserauthentication.firebaseio.com/Soko Uploads");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));


    }




}
