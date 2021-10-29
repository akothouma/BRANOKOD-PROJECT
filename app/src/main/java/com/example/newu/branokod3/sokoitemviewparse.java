package com.example.newu.branokod3;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
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

import static com.example.newu.branokod3.sokoview.CONTANTsokoparse;
import static com.example.newu.branokod3.sokoview.DESCsokoparse;
import static com.example.newu.branokod3.sokoview.EXPOsokoparse;
import static com.example.newu.branokod3.sokoview.PHOTOsokoparse;
import static com.example.newu.branokod3.sokoview.POSTtimesokoparse;
import static com.example.newu.branokod3.sokoview.TYPEsokoparse;

public class  sokoitemviewparse extends AppCompatActivity {
    private static final String TAG ="sokoitemviewparse" ;
    ImageButton imb1;
TextView contact,desc,expo;
FirebaseAuth auth;
ImageView im1;
DatabaseReference firebaseDatabasewishlist;
    DatabaseReference Databasepotentialclient;
    public static final String OWNERsokoparseprof="mwenyewe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sokoitemviewparse);

        imb1= findViewById(R.id.sokoitemparsewishview);
        im1= findViewById(R.id.imageView5);
        contact= findViewById(R.id.sokoitemparsecontact);
        desc= findViewById(R.id.sokoitemparsedesc);
        expo= findViewById(R.id.sokoitemviewpaeseexpo);
        firebaseDatabasewishlist=FirebaseDatabase.getInstance().getReference("sokowishlist");
        Databasepotentialclient=FirebaseDatabase.getInstance().getReference("Potential clientsS");
        auth=FirebaseAuth.getInstance();

        Intent intent1=getIntent();
          String description=intent1.getStringExtra(DESCsokoparse);
          String expected=intent1.getStringExtra(EXPOsokoparse);
          final String contactnu=intent1.getStringExtra(CONTANTsokoparse);
          String category =intent1.getStringExtra(TYPEsokoparse);
          String uri=intent1.getStringExtra(PHOTOsokoparse);
        String post=intent1.getStringExtra(POSTtimesokoparse);

        Log.d(TAG, "onCreate: "+ description);
        Log.d(TAG, "onCreate: "+ expected);
        Log.d(TAG, "onCreatephone: "+ contactnu);
        Log.d(TAG, "onCreate: "+ category);
        Log.d(TAG, "onCreate: "+ uri);

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
                        Toast.makeText(sokoitemviewparse.this,"Successfully added to wishlist",Toast.LENGTH_LONG).show();
                    }
                });
                Databasepotentialclient.child(contactnu).child(auth.getUid()).child(wishId).setValue(collectiveUploadsSoko);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(sokoitemviewparse.this,Profilesokoview.class);
                intent2.putExtra(OWNERsokoparseprof,contactnu);
                startActivity(intent2);

            }
        });






    }
}
