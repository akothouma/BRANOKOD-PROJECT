package com.example.newu.branokod3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import static com.example.newu.branokod3.mysokoitemshare.CONTANTsokoparse21;
import static com.example.newu.branokod3.mysokoitemshare.DESCsokoparse21;
import static com.example.newu.branokod3.mysokoitemshare.EXPOsokoparse21;
import static com.example.newu.branokod3.mysokoitemshare.PHOTOsokoparse21;
import static com.example.newu.branokod3.mysokoitemshare.POSTtimesokoparse21;
import static com.example.newu.branokod3.mysokoitemshare.TYPEsokoparse21;
import static com.example.newu.branokod3.sokoview.CONTANTsokoparse;
import static com.example.newu.branokod3.sokoview.DESCsokoparse;
import static com.example.newu.branokod3.sokoview.EXPOsokoparse;
import static com.example.newu.branokod3.sokoview.PHOTOsokoparse;
import static com.example.newu.branokod3.sokoview.POSTtimesokoparse;
import static com.example.newu.branokod3.sokoview.TYPEsokoparse;

public class mysokoitemshareparse extends AppCompatActivity {
    public DatabaseReference firebaseDatabasewishlist;
    DatabaseReference Databasepotentialclient;
    FirebaseAuth auth;
    TextView expo,desc;
    ImageButton imb1;
    ImageView im1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysokoitemshareparse);

        imb1= findViewById(R.id.sokoitemparsewishview);
        im1= findViewById(R.id.imageView5);

        desc= findViewById(R.id.sokoitemparsedesc);
        expo= findViewById(R.id.sokoitemviewpaeseexpo);
        firebaseDatabasewishlist= FirebaseDatabase.getInstance().getReference("sokowishlist");
        Databasepotentialclient=FirebaseDatabase.getInstance().getReference("Potential clientsS");
        auth= FirebaseAuth.getInstance();

        Intent intent1=getIntent();
        String description=intent1.getStringExtra(DESCsokoparse21);
        String expected=intent1.getStringExtra(EXPOsokoparse21);
        final String contactnu=intent1.getStringExtra(CONTANTsokoparse21);
        String category =intent1.getStringExtra(TYPEsokoparse21);
        String uri=intent1.getStringExtra(PHOTOsokoparse21);
        String post=intent1.getStringExtra(POSTtimesokoparse21);



        final CollectiveUploadsSoko collectiveUploadsSoko= new CollectiveUploadsSoko(description,expected,contactnu,category,post,uri);

        desc.setText(description);
        expo.setText(expected);
        Picasso.get().load(uri).fit().centerCrop().into(im1);

        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String wishId= firebaseDatabasewishlist.push().getKey();
                firebaseDatabasewishlist.child(auth.getUid()).child(wishId).setValue(collectiveUploadsSoko).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(mysokoitemshareparse.this,"Successfully added to wishlist",Toast.LENGTH_LONG).show();
                    }
                });
                Databasepotentialclient.child(contactnu).child(auth.getUid()).child(wishId).setValue(collectiveUploadsSoko);
            }
        });
    }
}
