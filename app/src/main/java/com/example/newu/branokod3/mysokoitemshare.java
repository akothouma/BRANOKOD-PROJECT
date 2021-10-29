package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mysokoitemshare extends AppCompatActivity implements SokoAdapter.OnItemClickListener {
    FirebaseAuth auth;
    RecyclerView recyclerView;
    ArrayList<CollectiveUploadsSoko>collectiveUploadsSokoList;
    DatabaseReference DatabaseSoko;
    SokoAdapter sokoAdapter;
    public static final String PHOTOsokoparse21="uri";
    public static final String DESCsokoparse21 ="description";
    public static final String EXPOsokoparse21 ="expected";
    public static final String CONTANTsokoparse21="contactnumber";
    public static final String TYPEsokoparse21="typeofproduct";
    public static final String POSTtimesokoparse21= "posttime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysokoitemshare);
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.thisismysokoitems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        collectiveUploadsSokoList = new ArrayList<>();

        DatabaseSoko = FirebaseDatabase.getInstance().getReference("Soko Uploads");
        DatabaseSoko.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CollectiveUploadsSoko collectiveUploadsSoko = postSnapshot.getValue(CollectiveUploadsSoko.class);
                    if (collectiveUploadsSoko.getContact().equals(auth.getUid())) {
                        collectiveUploadsSokoList.add(collectiveUploadsSoko);
                    }
                    sokoAdapter = new SokoAdapter(collectiveUploadsSokoList, mysokoitemshare.this);
                    recyclerView.setAdapter(sokoAdapter);
                    sokoAdapter.setListener(mysokoitemshare.this);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    @Override
    public void onItemClick(int pos) {
        CollectiveUploadsSoko current=collectiveUploadsSokoList.get(pos);
        String imagewanted=current.getUri();
        String desc=current.getDes();
        String type=current.getType();
        String contact=current.getContact();
        String expe =current.getExpo();
        String post=current.getPosttime();
        Intent intent=new Intent(mysokoitemshare.this,mysokoitemshareparse.class);
        intent.putExtra(PHOTOsokoparse21,imagewanted);
        intent.putExtra(DESCsokoparse21,desc);
        intent.putExtra(TYPEsokoparse21,type);
        intent.putExtra(CONTANTsokoparse21,contact);
        intent.putExtra(EXPOsokoparse21,expe);
        intent.putExtra(POSTtimesokoparse21,post);

    }
}
