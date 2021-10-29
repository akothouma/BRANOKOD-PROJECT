package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class potentialclientVarsity extends AppCompatActivity implements potentialclientAdapter.OnItemClickListener {

    private static final String TAG = "potentialclientVarsity";
    public static final String CONTACT1 = "getthem";
    public static final String ITEM1 = "seewhatsthere";
    public static final String PHOTO1 = "photo";
    public static final String CAMPUS1 = "campo";
    public static final String REG1 = "registrationno";
    public static final String WANO2 ="stringofvalues" ;


    potentialclientAdapter potentialClientAdapter;
    List<Profiledetails> potentialclientlist;
    DatabaseReference potesh,profiledp;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    String wanted,wanted1;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potentialclient_varsity);

        recyclerView=findViewById(R.id.varpotentialrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth=FirebaseAuth.getInstance();
        potesh= FirebaseDatabase.getInstance().getReference("Potential clientsV");
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
                            for (DataSnapshot ds2 : ds1.getChildren()) {
                                wanted1 = ds2.getKey();
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
                        potentialClientAdapter = new potentialclientAdapter(potentialclientlist, potentialclientVarsity.this);
                        recyclerView.setAdapter(potentialClientAdapter);
                        potentialClientAdapter.setListener(potentialclientVarsity.this);
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

        Intent in=new Intent(potentialclientVarsity.this,potentialcVarsityparse.class);

        in.putExtra(PHOTO1,uri);
        in.putExtra(REG1,name);
        in.putExtra(CAMPUS1,campus);
        in.putExtra(ITEM1,wanted);
        in.putStringArrayListExtra(WANO2,(ArrayList<String>)list);
        startActivity(in);
    }
}

