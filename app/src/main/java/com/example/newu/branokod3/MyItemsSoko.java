package com.example.newu.branokod3;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
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

public class MyItemsSoko extends AppCompatActivity {
    DatabaseReference firebaseDatabaseSoko,fidb;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    TextView payment;
    StorageReference st;
    int length;
    String theid;
    StorageReference uri;
    private MyItemsSokoAdapter myItemsSokoAdapter;
    public static final String NUM = "itemno";
    public static final String TAG = "MyItemsSoko";
    CollectiveUploadsSoko collectiveUploadsSoko;
    private List<CollectiveUploadsSoko> collectiveUploadsSokoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_soko);
        firebaseDatabaseSoko = FirebaseDatabase.getInstance().getReference("Soko Uploads");
        fidb=FirebaseDatabase.getInstance().getReference("Soko Uploads").child("posttime");
        recyclerView = findViewById(R.id.my_recycler_viewmyItemssoko);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectiveUploadsSokoList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        payment = findViewById(R.id.textView6);



        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyItemsSoko.this, sokopayment.class);
                length = collectiveUploadsSokoList.size();
                intent.putExtra(NUM, length);
                startActivity(intent);


            }
        });

        firebaseDatabaseSoko.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    collectiveUploadsSoko = postSnapshot.getValue(CollectiveUploadsSoko.class);
                    if (collectiveUploadsSoko.getContact().equals(auth.getUid())) {
                        theid=postSnapshot.getKey();
                        collectiveUploadsSokoList.add(collectiveUploadsSoko);
                        String photo=collectiveUploadsSoko.getUri();
                         uri= FirebaseStorage.getInstance().getReferenceFromUrl(photo);
                    }
                }
                myItemsSokoAdapter = new MyItemsSokoAdapter(collectiveUploadsSokoList, MyItemsSoko.this);
                recyclerView.setAdapter(myItemsSokoAdapter);

                ItemTouchHelper simpleCallback=new ItemTouchHelper(new Swipping(0,ItemTouchHelper.LEFT,myItemsSokoAdapter,new ColorDrawable
                        (Color.rgb(0,57,4)),theid,uri));
                simpleCallback.attachToRecyclerView(recyclerView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        long cutoff = new Date().getTime() - TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);
        Query query=firebaseDatabaseSoko.orderByChild("posttime").endAt(cutoff +"\ufBff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    if (itemSnapshot.getKey().equals(theid)) {
                        myItemsSokoAdapter.delete(itemSnapshot, theid,uri);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });


    }

}
