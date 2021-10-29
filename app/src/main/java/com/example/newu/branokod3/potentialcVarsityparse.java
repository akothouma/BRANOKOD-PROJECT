package com.example.newu.branokod3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import static com.example.newu.branokod3.potentialclientVarsity.CAMPUS1;
import static com.example.newu.branokod3.potentialclientVarsity.ITEM1;
import static com.example.newu.branokod3.potentialclientVarsity.PHOTO1;
import static com.example.newu.branokod3.potentialclientVarsity.REG1;
import static com.example.newu.branokod3.potentialclientVarsity.WANO2;

public class potentialcVarsityparse extends AppCompatActivity {

    public static final String YEYE = "persona";
    private static final String TAG = "potentialcVarsityparse ";

    List<Profiledetails> profiledetails;
    DatabaseReference fireitem;
    TextView t1,t2,t3;
    ImageView imageView;
    public static  final String WANO1="itemid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potentialc_varsityparse);

        fireitem= FirebaseDatabase.getInstance().getReference("Varsitywishlist");
        t1=(TextView)findViewById(R.id.wStudReg);
        t2=(TextView)findViewById(R.id.wStudCampus);
        t3= (TextView)findViewById(R.id.ioi);
        imageView=(ImageView)findViewById(R.id.imageView5) ;

        Intent intent=getIntent();
        final String itemdetails=intent.getStringExtra(ITEM1);
        String person=intent.getStringExtra(REG1);
        String campo=intent.getStringExtra(CAMPUS1);
        String photo=intent.getStringExtra(PHOTO1);
        final ArrayList<String> personal=intent.getStringArrayListExtra(WANO2);

        Picasso.get().load(photo).centerCrop().fit().into(imageView);
        t1.setText(person);
        t2.setText(campo);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(potentialcVarsityparse.this,Varsityitemofinterest.class);
                intent1.putExtra(WANO1,itemdetails);
                Log.d(TAG, "onClick: "+ itemdetails);
                intent1.putExtra(YEYE,(ArrayList<String>)personal);
                startActivity(intent1);
            }
        });


    }
}
