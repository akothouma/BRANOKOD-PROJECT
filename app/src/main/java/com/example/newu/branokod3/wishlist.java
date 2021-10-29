package com.example.newu.branokod3;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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


public class wishlist extends AppCompatActivity implements WishlistAdapter.OnItemClickListener {
    private static final String TAG ="wishlist";
    public static final String PHOTOW="uri";
    public static final String DESC ="description";
    public static final String EXPO ="expected";
    public static final String CONTANT="getowner";
   RecyclerView recyclerView;
    TextView view;
    private WishlistAdapter wishlistAdapter;
    DatabaseReference wishlist;
    FirebaseAuth auth;
    String theid;

    List<CollectiveUploadsSoko>collectiveUploadsSokos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        recyclerView = findViewById(R.id.wishlistRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishlist=FirebaseDatabase.getInstance().getReference("sokowishlist");
        auth=FirebaseAuth.getInstance();

        collectiveUploadsSokos=new ArrayList<>();


        wishlist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String wanted=ds.getKey();
                        Log.d(TAG, "onDataChange: "+ wanted);
                        if(auth.getUid().equals(wanted)) {
                            for(DataSnapshot dataSnapshot1:ds.getChildren()) {
                                theid=dataSnapshot1.getKey();
                                Log.d(TAG, "onDataChange1: "+ theid);
                                CollectiveUploadsSoko cs = dataSnapshot1.getValue(CollectiveUploadsSoko.class);
                                collectiveUploadsSokos.add(cs);
                            }

                    }
                }
                wishlistAdapter=new WishlistAdapter(collectiveUploadsSokos,wishlist.this);
                recyclerView.setAdapter(wishlistAdapter);
                wishlistAdapter.setListener(wishlist.this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        long cutoff = new Date().getTime() - TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);
        Query query=wishlist.child(auth.getUid()).orderByChild("posttime").endAt(cutoff +"\ufBff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    if (itemSnapshot.getKey().equals(theid)) {
                        wishlistAdapter.delete(itemSnapshot, theid);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });




    }

    @Override
    public void OnItemClick(int position) {
        CollectiveUploadsSoko current=collectiveUploadsSokos.get(position);
        Intent intent=new Intent(wishlist.this,wishlistparse.class);
        intent.putExtra(PHOTOW, current.getUri());
        intent.putExtra(DESC,current.getDes());
        intent.putExtra(CONTANT,current.getContact());
        intent.putExtra(EXPO,current.getExpo());

        startActivity(intent);
    }
}

