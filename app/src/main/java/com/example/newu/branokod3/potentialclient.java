package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class potentialclient extends AppCompatActivity implements potentialclientAdapter.OnItemClickListener {
    private static final String TAG = "potentialclients";
    public static final String CONTACT = "getthem";
    public static final String ITEM = "seewhatsthere";
    public static final String PHOTO = "photo";
    public static final String CAMPUS = "campo";
    public static final String REG = "registrationno";
    public static final String PERSONA = "thewanted";

    potentialclientAdapter potentialClientAdapter;
    List<Profiledetails>potentialclientlist;
    DatabaseReference potesh,profiledp;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    String wanted,wanted1;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potentialclient);

        recyclerView=findViewById(R.id.sokopotentialrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth=FirebaseAuth.getInstance();
        potesh= FirebaseDatabase.getInstance().getReference("Potential clientsS");
        profiledp=FirebaseDatabase.getInstance().getReference("Profile");


        potentialclientlist=new ArrayList<>();
        list=new ArrayList<>();


        potesh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (auth.getUid().equals(ds.getKey())) {
                        for (DataSnapshot ds1 : ds.getChildren()) {
                            wanted = ds1.getKey();
                            for (DataSnapshot ds2:ds1.getChildren()){
                                wanted1= ds2.getKey();
                                Log.d(TAG, "onDataChangeprof: " + wanted);
                                Log.d(TAG, "onDataChangeprof1: " + wanted1);
                                list.add(wanted1);
                            }
                        }
                    }
                }


                profiledp.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.getKey().equals(wanted)) {
                                Profiledetails pd = ds.getValue(Profiledetails.class);
                                potentialclientlist.add(pd);
                            }
                        }
                        potentialClientAdapter = new potentialclientAdapter(potentialclientlist, potentialclient.this);
                        recyclerView.setAdapter(potentialClientAdapter);
                        potentialClientAdapter.setListener(potentialclient.this);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(int pos) {
        Profiledetails pd=potentialclientlist.get(pos);
        String name=pd.getRegno();
        String uri=pd.getUri();
        String campus=pd.getCam();

        Intent in=new Intent(potentialclient.this,potentialcSokoparse.class);

        in.putExtra(PHOTO,uri);
        in.putExtra(REG,name);
        in.putExtra(CAMPUS,campus);
        in.putExtra(ITEM,(ArrayList<String>)list);
        in.putExtra(PERSONA,wanted);
        startActivity(in);
    }
}
