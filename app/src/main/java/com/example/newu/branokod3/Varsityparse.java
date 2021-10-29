package com.example.newu.branokod3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
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

import static com.example.newu.branokod3.Versityview.CONTANTvarsityparse;
import static com.example.newu.branokod3.Versityview.DESCvarsityparse;
import static com.example.newu.branokod3.Versityview.DURATIONvarsityparse;
import static com.example.newu.branokod3.Versityview.PHOTOvarsityparse;
import static com.example.newu.branokod3.Versityview.POSTTIMEvarsityparse;
import static com.example.newu.branokod3.Versityview.PRICEvarsityparse;
import static com.example.newu.branokod3.Versityview.TYPEvarsityparse;

public class Varsityparse extends AppCompatActivity {

     ImageButton imb1;
     ImageView im1;
     TextView desc,contact,price1;
     DatabaseReference firebaseDatabasewishlist,Databasepotentialclient;
     FirebaseAuth auth;
     public final static String ownersVarsityprof="yakee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsityparse);

        imb1= findViewById(R.id.varparsewishview);
        im1= findViewById(R.id.imageView5);
        contact= findViewById(R.id.varparsecontact);
        desc= findViewById(R.id.varparsedesc);
        price1= findViewById(R.id.varparseitemviewprice);
        firebaseDatabasewishlist= FirebaseDatabase.getInstance().getReference("Varsitywishlist");
        Databasepotentialclient=FirebaseDatabase.getInstance().getReference("Potential clientsV");
        auth= FirebaseAuth.getInstance();

        Intent intent1=getIntent();
        String description=intent1.getStringExtra(DESCvarsityparse);
        String price=intent1.getStringExtra(PRICEvarsityparse);
        final String contactnu=intent1.getStringExtra(CONTANTvarsityparse);
        String time1=intent1.getStringExtra(DURATIONvarsityparse);
        String category =intent1.getStringExtra(TYPEvarsityparse);
        String uri=intent1.getStringExtra(PHOTOvarsityparse);
        String posttime=intent1.getStringExtra(POSTTIMEvarsityparse);

        final CollectiveUploadsVarsity collectiveUploadsVarsity=new CollectiveUploadsVarsity(description,price,time1,category,posttime,uri,contactnu);

        desc.setText(description);
        price1.setText(price);
        Picasso.get().load(uri).fit().centerCrop().into(im1);

        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wishId= firebaseDatabasewishlist.push().getKey();
                firebaseDatabasewishlist.child(auth.getUid()).child(wishId).setValue(collectiveUploadsVarsity).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Varsityparse.this,"Successfully added to wishlist",Toast.LENGTH_LONG).show();
                    }
                });
                String poteshid=Databasepotentialclient.push().getKey();
                Databasepotentialclient.child(contactnu).child(auth.getUid()).child(poteshid).setValue(collectiveUploadsVarsity);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Varsityparse.this,ProfileVarsityview.class);
                intent2.putExtra(ownersVarsityprof,contactnu);
                startActivity(intent2);

            }
        });




    }
}
