package com.example.newu.branokod3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import static com.example.newu.branokod3.myvarsityitemshare.CONTANTvarsityparse22;
import static com.example.newu.branokod3.myvarsityitemshare.DESCvarsityparse22;
import static com.example.newu.branokod3.myvarsityitemshare.DURATIONvarsityparse22;
import static com.example.newu.branokod3.myvarsityitemshare.PHOTOvarsityparse22;
import static com.example.newu.branokod3.myvarsityitemshare.POSTTIMEvarsityparse22;
import static com.example.newu.branokod3.myvarsityitemshare.PRICEvarsityparse22;
import static com.example.newu.branokod3.myvarsityitemshare.TYPEvarsityparse22;

public class myvarsityitemshareparse extends AppCompatActivity {

    public DatabaseReference firebaseDatabasewishlist;
     DatabaseReference Databasepotentialclient;
     FirebaseAuth auth;
     TextView price1,desc;
    ImageButton imb1;
    ImageView im1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvarsityitemshareparse);

        imb1 = findViewById(R.id.varparsewishview);
        im1 = findViewById(R.id.imageView5);
        desc = findViewById(R.id.varparsedesc);
        price1 = findViewById(R.id.varparseitemviewprice);
        firebaseDatabasewishlist = FirebaseDatabase.getInstance().getReference("Varsitywishlist");
        Databasepotentialclient = FirebaseDatabase.getInstance().getReference("Potential clientsV");
        auth = FirebaseAuth.getInstance();

        Intent intent1 = getIntent();
        String description = intent1.getStringExtra(DESCvarsityparse22);
        String price = intent1.getStringExtra(PRICEvarsityparse22);
        final String contactnu = intent1.getStringExtra(CONTANTvarsityparse22);
        String time1 = intent1.getStringExtra(DURATIONvarsityparse22);
        String category = intent1.getStringExtra(TYPEvarsityparse22);
        String uri = intent1.getStringExtra(PHOTOvarsityparse22);
        String posttime = intent1.getStringExtra(POSTTIMEvarsityparse22);

        final CollectiveUploadsVarsity collectiveUploadsVarsity = new CollectiveUploadsVarsity(description, price, time1, category, posttime, uri, contactnu);

        desc.setText(description);
        price1.setText(price);
        Picasso.get().load(uri).fit().centerCrop().into(im1);

        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wishId = firebaseDatabasewishlist.push().getKey();
                firebaseDatabasewishlist.child(auth.getUid()).child(wishId).setValue(collectiveUploadsVarsity).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(myvarsityitemshareparse.this, "Successfully added to wishlist", Toast.LENGTH_LONG).show();
                    }
                });
                Databasepotentialclient.child(contactnu).child(auth.getUid()).child(wishId).setValue(collectiveUploadsVarsity);
            }
        });
    }
}
