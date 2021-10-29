 package com.example.newu.branokod3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.newu.branokod3.wishlist.CONTANT;
import static com.example.newu.branokod3.wishlist.DESC;
import static com.example.newu.branokod3.wishlist.EXPO;
import static com.example.newu.branokod3.wishlist.PHOTOW;

 public class wishlistparse extends AppCompatActivity {
     private static final String TAG = "wishlistparse";
     public static final String WAN="contact";
     ImageView imageView;
     TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlistparse);
        imageView= findViewById(R.id.wparseimage);
        t1= findViewById(R.id.wSokodesc);
        t2= findViewById(R.id.wSokoexpe);
        t3= findViewById(R.id.wcontactexchange);

        final Intent intent=getIntent();
        String photo=intent.getStringExtra(PHOTOW);
        String description=intent.getStringExtra(DESC);
        String expo=intent.getStringExtra(EXPO);
        final String contant=intent.getStringExtra(CONTANT);
        Log.d(TAG, "onCreate: " + contant);

        Picasso.get().load(photo).fit().centerCrop().placeholder(R.drawable.blacknwhite).into(imageView);
        t1.setText(description);
        t2.setText(expo);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(wishlistparse.this,profileparsewish.class);
                intent1.putExtra(WAN,contant);
                startActivity(intent1);

            }
        });
    }
}
