package com.example.newu.branokod3;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class  MyItemsVarsity extends AppCompatActivity {
    private static final String TAG = "MyItemsVarsity";
    DatabaseReference firebaseDatabaseVarsity;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    TextView payment;
    private MyItemsVarsityAdapter myItemsVarsityAdapter;
    public static final String NUMV = "itemno";
    public static final String PRICE = "productprice";
    public static final String TIME = "advertismentperiod";
    private List<CollectiveUploadsVarsity> collectiveUploadsVarsityList;
    String theid;
    StorageReference uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_varsity);
        firebaseDatabaseVarsity = FirebaseDatabase.getInstance().getReference("Varsity Uploads");
        recyclerView = findViewById(R.id.my_recyclermyitemsVarsity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectiveUploadsVarsityList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        payment = findViewById(R.id.textViewpayment);
        final List<String> timeList = new ArrayList<>();
        final List<String> priceList = new ArrayList<>();


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < collectiveUploadsVarsityList.size(); i++) {
                    String wantedtime = collectiveUploadsVarsityList.get(i).getDuration();
                    String wantedprice = collectiveUploadsVarsityList.get(i).getMuch();
                    timeList.add(wantedtime);
                    priceList.add(wantedprice);
                }
                Intent intent = new Intent(MyItemsVarsity.this, varsitypayment.class);
                intent.putExtra(PRICE, (ArrayList<String>) priceList);
                intent.putExtra(TIME, (ArrayList<String>) timeList);
                intent.putExtra(NUMV, collectiveUploadsVarsityList.size());
                Log.d(TAG, "onClickprice: " + priceList);
                Log.d(TAG, "onClicktime: " + timeList);
                startActivity(intent);

            }
        });

        firebaseDatabaseVarsity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CollectiveUploadsVarsity collectiveV = postSnapshot.getValue(CollectiveUploadsVarsity.class);

                     String photo= collectiveV.getUri();
                     uri=FirebaseStorage.getInstance().getReferenceFromUrl(photo);
                    if (collectiveV.getContacturl().equals(auth.getUid())) {
                        theid=postSnapshot.getKey();
                        collectiveUploadsVarsityList.add(collectiveV);
                    }
                }
                myItemsVarsityAdapter = new MyItemsVarsityAdapter(collectiveUploadsVarsityList, MyItemsVarsity.this);
                recyclerView.setAdapter(myItemsVarsityAdapter);
                ItemTouchHelper simpleCallback=new ItemTouchHelper(new Swippingvarsity(0,ItemTouchHelper.LEFT,myItemsVarsityAdapter,new ColorDrawable
                        (Color.rgb(0,57,4)),theid,uri));
                simpleCallback.attachToRecyclerView(recyclerView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


            Query query=firebaseDatabaseVarsity.orderByChild("duration"+"\ufBff");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                        CollectiveUploadsVarsity collectiveV = datasnapshot1.getValue(CollectiveUploadsVarsity.class);

                        if (collectiveV.getDuration().equals(String.valueOf(3)) ){
                            long cutoff3 = new Date().getTime() - TimeUnit.MILLISECONDS.convert(21, TimeUnit.DAYS);
                            final String needid = datasnapshot1.getKey();
                            Log.d(TAG, "onDataChange3: "+ needid);
                            Query query1 = firebaseDatabaseVarsity.orderByChild("posttime").endAt(cutoff3 + "\ufBff");
                            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if(snapshot.getKey().equals(needid)) {
                                            myItemsVarsityAdapter.delete(snapshot, needid, uri);
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
                            Log.d(TAG, "onDataChange4: "+ needid1);
                            Query query2 = firebaseDatabaseVarsity.orderByChild("posttime").endAt(cutoff4 + "\ufBff");
                            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if (snapshot.getKey().equals(needid1)) {
                                            myItemsVarsityAdapter.delete1(snapshot, needid1, uri);
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
                            Log.d(TAG, "onDataChange8: "+ needid2);
                            Query query3= firebaseDatabaseVarsity.orderByChild("posttime").endAt(cutoff8 + "\ufBff");
                            query3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if(snapshot.getKey().equals(needid2)) {
                                            myItemsVarsityAdapter.delete2(snapshot, needid2, uri);
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
    }





