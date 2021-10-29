package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myvarsityitemshare extends AppCompatActivity implements VarsityAdapter.OnItemClickListener {
    FirebaseAuth auth;
    RecyclerView recyclerView;
    ArrayList<CollectiveUploadsVarsity> collectiveUploadsVarsityList;
    DatabaseReference DatabaseVarsity;
    VarsityAdapter varsityAdapter;
    public static final String PHOTOvarsityparse22="uri";
    public static final String DESCvarsityparse22 ="description";
    public static final String PRICEvarsityparse22 ="expected";
    public static final String CONTANTvarsityparse22="contactnumber";
    public static final String TYPEvarsityparse22="typeofproduct";
    public static final String DURATIONvarsityparse22="selltime";
    public static final String POSTTIMEvarsityparse22="posttime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvarsityitemshare);

        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.myvarsityshareitems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectiveUploadsVarsityList = new ArrayList<>();

        DatabaseVarsity = FirebaseDatabase.getInstance().getReference("Varsity Uploads");

        DatabaseVarsity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CollectiveUploadsVarsity collectiveUploadsVarsity = postSnapshot.getValue(CollectiveUploadsVarsity.class);
                    if (collectiveUploadsVarsity.getContacturl().equals(auth.getUid())) {
                        collectiveUploadsVarsityList.add(collectiveUploadsVarsity);
                    }
                    varsityAdapter = new VarsityAdapter(collectiveUploadsVarsityList, myvarsityitemshare.this);
                    recyclerView.setAdapter(varsityAdapter);
                    varsityAdapter.setListener(myvarsityitemshare.this);
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
        CollectiveUploadsVarsity current=collectiveUploadsVarsityList.get(pos);
        String imagewanted=current.getUri();
        String desc=current.getDesc();
        String type=current.getType();
        String contact=current.getContacturl();
        String price =current.getMuch();
        String time=current.getDuration();
        String post=current.getPosttime();


        Intent intent=new Intent(myvarsityitemshare.this,myvarsityitemshareparse.class);
        intent.putExtra(PHOTOvarsityparse22,imagewanted);
        intent.putExtra(DESCvarsityparse22,desc);
        intent.putExtra(TYPEvarsityparse22,type);
        intent.putExtra(CONTANTvarsityparse22,contact);
        intent.putExtra(PRICEvarsityparse22,price);
        intent.putExtra(DURATIONvarsityparse22,time);
        intent.putExtra(POSTTIMEvarsityparse22,post);
        startActivity(intent);

    }
}
