package com.example.newu.branokod3;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class wishlistVarsity extends AppCompatActivity implements WishlistAdapterVarsity.OnItemClickListener {
    private static final String TAG ="wishlistVarsity";
    public static final String PHOTOW1="uri";
    public static final String DESC1="description";
    public static final String PRICE1 ="much";
    public static final String CONTANT1="getowner";
    RecyclerView recyclerView;
    TextView view;
     WishlistAdapterVarsity wishlistAdapter;
    DatabaseReference wishlist;
    FirebaseAuth auth;


    List<CollectiveUploadsVarsity> collectiveUploadsVarsityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_varsity);
        recyclerView = findViewById(R.id.varwishlistrecyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishlist= FirebaseDatabase.getInstance().getReference("Varsitywishlist");
        auth=FirebaseAuth.getInstance();

        collectiveUploadsVarsityList=new ArrayList<>();

        wishlist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String wanted = ds.getKey();
                    Log.d(TAG, "onDataChange: " + wanted);
                    if (auth.getUid().equals(wanted)) {
                        for (DataSnapshot dataSnapshot1 : ds.getChildren()) {
                            String wanted1 = dataSnapshot1.getKey();
                            Log.d(TAG, "onDataChange1: " + wanted1);
                            CollectiveUploadsVarsity cv = dataSnapshot1.getValue(CollectiveUploadsVarsity.class);
                            collectiveUploadsVarsityList.add(cv);

                        }

                    }
                }
                wishlistAdapter=new WishlistAdapterVarsity(collectiveUploadsVarsityList,wishlistVarsity.this);
                recyclerView.setAdapter(wishlistAdapter);
                wishlistAdapter.setListener(wishlistVarsity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query=wishlist.orderByChild("duration"+"\ufBff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    int len=collectiveUploadsVarsityList.size();
                    for(int i=0;i<len;i++) {
                        String vwala = String.valueOf(collectiveUploadsVarsityList.get(i));
                        CollectiveUploadsVarsity collectiveV = datasnapshot1.child(vwala).getValue(CollectiveUploadsVarsity.class);


                        if (collectiveV.getDuration().equals(String.valueOf(3))) {
                            long cutoff3 = new Date().getTime() - TimeUnit.MILLISECONDS.convert(21, TimeUnit.DAYS);
                            final String needid = datasnapshot1.getKey();
                            Log.d(TAG, "onDataChange3: " + needid);
                            Query query1 = wishlist.orderByChild("posttime").endAt(cutoff3 + "\ufBff");
                            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if (snapshot.getKey().equals(needid)) {
                                            wishlistAdapter.delete(snapshot, needid);
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        if (collectiveV.getDuration().equals(String.valueOf(4))) {
                            long cutoff4 = new Date().getTime() - TimeUnit.MILLISECONDS.convert(28, TimeUnit.DAYS);
                            final String needid1 = datasnapshot1.getKey();
                            Log.d(TAG, "onDataChange4: " + needid1);
                            Query query2 = wishlist.orderByChild("posttime").endAt(cutoff4 + "\ufBff");
                            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if (snapshot.getKey().equals(needid1)) {
                                            wishlistAdapter.delete1(snapshot, needid1);
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        if (collectiveV.getDuration().equals(String.valueOf(8))) {
                            long cutoff8 = new Date().getTime() - TimeUnit.MILLISECONDS.convert(56, TimeUnit.DAYS);
                            final String needid2 = datasnapshot1.getKey();
                            Log.d(TAG, "onDataChange8: " + needid2);
                            Query query3 = wishlist.orderByChild("posttime").endAt(cutoff8 + "\ufBff");
                            query3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if (snapshot.getKey().equals(needid2)) {
                                            wishlistAdapter.delete2(snapshot, needid2);
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    @Override
    public void OnItemClick(int position) {
        CollectiveUploadsVarsity current=collectiveUploadsVarsityList.get(position);
        Intent intent=new Intent(wishlistVarsity.this,wishlistparseVarsity.class);
        intent.putExtra(PHOTOW1, current.getUri());
        intent.putExtra(DESC1,current.getDesc());
        intent.putExtra(CONTANT1,current.getContacturl());
        intent.putExtra(PRICE1,current.getMuch());

        startActivity(intent);

    }
}
