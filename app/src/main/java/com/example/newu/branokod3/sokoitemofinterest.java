package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

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

import static com.example.newu.branokod3.potentialcSokoparse.WANO;
import static com.example.newu.branokod3.potentialcSokoparse.WANO1;

public class sokoitemofinterest extends AppCompatActivity implements WishlistAdapter.OnItemClickListener {
    private static final String TAG ="sokoitemofinterest" ;
    RecyclerView recyclerView;
    DatabaseReference fireitem;
    WishlistAdapter wishlistAdapter;
    FirebaseAuth auth;
    List<CollectiveUploadsSoko>collectiveUploadsSokos;

    public static final String PHOTOWZ="uri";
    public static final String DESCZ="description";
    public static final String EXPEC ="expected";
     int len;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sokoitemofinterest);

        recyclerView = findViewById(R.id.sokoitemofinterestrecyc);
        fireitem = FirebaseDatabase.getInstance().getReference("Potential clientsS");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        final String persona = intent.getStringExtra(WANO1);
        final ArrayList<String> hizoid = intent.getStringArrayListExtra(WANO);
        Log.d(TAG, "onCreate: " + persona);
        len = hizoid.size();

        collectiveUploadsSokos = new ArrayList<>();

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
                                    String vwala = hizoid.get(i);
                                    CollectiveUploadsSoko cs = ds1.child(vwala).
                                            getValue(CollectiveUploadsSoko.class);
                                    collectiveUploadsSokos.add(cs);
                                }
                            }
                        }
                    }
                    wishlistAdapter = new WishlistAdapter(collectiveUploadsSokos, sokoitemofinterest.this);
                    recyclerView.setAdapter(wishlistAdapter);
                    wishlistAdapter.setListener(sokoitemofinterest.this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fireitem.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String poi = dataSnapshot.getKey();
                    for (DataSnapshot dataSnapshot1 : ds.getChildren()) {
                        final String koi = dataSnapshot1.getKey();
                        long cutoff = new Date().getTime() - TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);
                        Query query = fireitem.child(auth.getUid()).child(poi).orderByChild("posttime").endAt(cutoff + "\ufBff");
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                                    if (itemSnapshot.getKey().equals(koi)) {
                                        wishlistAdapter.delete12(itemSnapshot, koi);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        CollectiveUploadsSoko current=collectiveUploadsSokos.get(position);
        Intent intent21=new Intent(sokoitemofinterest.this,sokoitemofinterestparse.class);
        intent21.putExtra(PHOTOWZ, current.getUri());
        intent21.putExtra(DESCZ,current.getDes());
        intent21.putExtra(EXPEC,current.getExpo());

        startActivity(intent21);
    }
}
