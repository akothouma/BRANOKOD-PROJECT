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

import static com.example.newu.branokod3.potentialcVarsityparse.WANO1;
import static com.example.newu.branokod3.potentialcVarsityparse.YEYE;

public class Varsityitemofinterest extends AppCompatActivity implements WishlistAdapter.OnItemClickListener, WishlistAdapterVarsity.OnItemClickListener {

    private static final String TAG ="varsityitemofinterest" ;
    RecyclerView recyclerView;
    DatabaseReference fireitem;
    WishlistAdapterVarsity wishlistAdapter;
    FirebaseAuth auth;
    List<CollectiveUploadsVarsity> collectiveUploadsVarsities;

    public static final String PHOTOWZ1="uri";
    public static final String DESCZ1="description";
    public static final String PRICE1 ="much";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsityitemofinterest);

        recyclerView=findViewById(R.id.varsityioirecyc);
        fireitem= FirebaseDatabase.getInstance().getReference("Potential clientsV");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth=FirebaseAuth.getInstance();
        Intent intent=getIntent();
        final String persona=intent.getStringExtra(WANO1);
        final ArrayList<String> hizoid=intent.getStringArrayListExtra(YEYE);
        Log.d(TAG, "onCreatevioi: " + persona);
        Log.d(TAG, "onCreatevioi1: " + hizoid);
        collectiveUploadsVarsities=new ArrayList<>();
        final int len=hizoid.size();
        Log.d(TAG, "onCreate: "+len);



            fireitem.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String wanted = ds.getKey();
                        if (auth.getUid().equals(wanted)) {
                            for (DataSnapshot ds1 : ds.getChildren()) {
                                if (ds1.getKey().equals(persona)) {
                                    Log.d(TAG, "onDataChange: " + ds1.getKey());
                                    for (int i = 0; i < len; i++) {
                                        String vwala=hizoid.get(i);
                                        CollectiveUploadsVarsity cv = ds1.child(vwala).
                                        getValue(CollectiveUploadsVarsity.class);
                                        collectiveUploadsVarsities.add(cv);
                                    }
                                }
                            }
                        }
                    } wishlistAdapter = new WishlistAdapterVarsity(collectiveUploadsVarsities, Varsityitemofinterest.this);
                    recyclerView.setAdapter(wishlistAdapter);
                    wishlistAdapter.setListener(Varsityitemofinterest.this);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            }

    @Override
    public void OnItemClick(int position) {
        CollectiveUploadsVarsity current=collectiveUploadsVarsities.get(position);
        Intent intent=new Intent(Varsityitemofinterest.this,Varsityitemofinterestparse.class);
        intent.putExtra(PHOTOWZ1, current.getUri());
        intent.putExtra(DESCZ1,current.getDesc());
        intent.putExtra(PRICE1,current.getMuch());

        startActivity(intent);

    }
}
