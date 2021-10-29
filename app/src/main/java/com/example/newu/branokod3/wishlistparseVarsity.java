package com.example.newu.branokod3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.newu.branokod3.wishlistVarsity.CONTANT1;
import static com.example.newu.branokod3.wishlistVarsity.DESC1;
import static com.example.newu.branokod3.wishlistVarsity.PHOTOW1;
import static com.example.newu.branokod3.wishlistVarsity.PRICE1;

public class wishlistparseVarsity extends AppCompatActivity {

    private static final String TAG = "wishlistparseVarsity";
    public static final String WAN = "contact";
    ImageView imageView;
    TextView t1, t2, t3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlistparse_varsity);

        imageView = findViewById(R.id.wparseimage);
        t1 = findViewById(R.id.wVardesc);
        t2 = findViewById(R.id.wVarprice);
        t3 = findViewById(R.id.wcontactexchange);

        final Intent intent = getIntent();
        String photo = intent.getStringExtra(PHOTOW1);
        String description = intent.getStringExtra(DESC1);
        String price = intent.getStringExtra(PRICE1);
        final String contant = intent.getStringExtra(CONTANT1);


        Picasso.get().load(photo).fit().centerCrop().placeholder(R.drawable.blacknwhite).into(imageView);
        t1.setText(description);
        t2.setText(price);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(wishlistparseVarsity.this, profileparsewishVarsity.class);
                intent1.putExtra(WAN, contant);
                startActivity(intent1);
            }

        });
    }
}
