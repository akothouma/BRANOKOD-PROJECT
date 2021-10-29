package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.newu.branokod3.wishlistparseVarsity.WAN;

public class profileparsewishVarsity extends AppCompatActivity {

     DatabaseReference firebaseprofile;
     FirebaseAuth auth;
    RecyclerView recyclerView;
    List<Profiledetails>profiledetailsList;
    ProfileAdapter profileAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileparsewish_varsity);


 firebaseprofile = FirebaseDatabase.getInstance().getReference("Profile");
         auth= FirebaseAuth.getInstance();
         recyclerView=findViewById(R.id.recycparseVarprof) ;
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

         profiledetailsList=new ArrayList<>();

        firebaseprofile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Intent intent = getIntent();
                    String contact = intent.getStringExtra(WAN);
                    if (ds.getKey().equals(contact)) {
                        Profiledetails pd = ds.getValue(Profiledetails.class);
                        profiledetailsList.add(pd);
                        profileAdapter = new ProfileAdapter(profiledetailsList, profileparsewishVarsity.this);
                        recyclerView.setAdapter(profileAdapter);

                    }
                }
            }
@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });
}
}