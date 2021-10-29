package com.example.newu.branokod3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.newu.branokod3.potentialclient.CAMPUS;
import static com.example.newu.branokod3.potentialclient.CONTACT;
import static com.example.newu.branokod3.potentialclient.ITEM;
import static com.example.newu.branokod3.potentialclient.PERSONA;
import static com.example.newu.branokod3.potentialclient.PHOTO;
import static com.example.newu.branokod3.potentialclient.REG;
import static com.example.newu.branokod3.potentialclientVarsity.WANO2;

public class potentialcSokoparse extends AppCompatActivity {

    List<Profiledetails> profiledetails;
    DatabaseReference fireitem;
    TextView t1,t2,t3;
    ImageView imageView;
    public static  final String WANO="itemid";
    public static  final String WANO1="persona";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potentialc_sokoparse);
        fireitem= FirebaseDatabase.getInstance().getReference("sokowishlist");
        t1=(TextView)findViewById(R.id.wStudReg);
        t2=(TextView)findViewById(R.id.wStudCampus);
        t3= (TextView)findViewById(R.id.ioi);
        imageView=(ImageView)findViewById(R.id.imageView5) ;

        Intent intent=getIntent();
        final ArrayList<String> itemdetails=intent.getStringArrayListExtra(ITEM);;
        String person=intent.getStringExtra(REG);
        String campo=intent.getStringExtra(CAMPUS);
        String photo=intent.getStringExtra(PHOTO);
        final String persona=intent.getStringExtra(PERSONA);

        Picasso.get().load(photo).centerCrop().fit().into(imageView);
        t1.setText(person);
        t2.setText(campo);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(potentialcSokoparse.this,sokoitemofinterest.class);
                intent1.putExtra(WANO,itemdetails);
                intent1.putExtra(WANO1,persona);
                startActivity(intent1);
            }
        });




    }
}
