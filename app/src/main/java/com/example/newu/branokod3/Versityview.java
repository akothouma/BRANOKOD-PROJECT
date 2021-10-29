package com.example.newu.branokod3;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class Versityview extends AppCompatActivity implements VarsityAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private VarsityAdapter varsityAdapter;
    FirebaseAuth auth;
    public static final String PHOTOvarsityparse="uri";
    public static final String DESCvarsityparse ="description";
    public static final String PRICEvarsityparse ="expected";
    public static final String CONTANTvarsityparse="contactnumber";
    public static final String TYPEvarsityparse="typeofproduct";
    public static final String DURATIONvarsityparse="selltime";
    public static final String POSTTIMEvarsityparse="posttime";
    TextView tv,tv1;
    public DatabaseReference DatabaseVarsity;
    public List<CollectiveUploadsVarsity> collectiveUploadsVarsityList;
    ImageButton ib;
    List<CollectiveUploadsVarsity> grouped;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versityview);

        recyclerView = findViewById(R.id.my_recycler_viewVersity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         auth=FirebaseAuth.getInstance();
         tv=(TextView)findViewById(R.id.textView8);
        tv1=(TextView) findViewById(R.id.sokosearchedittext);
        ib=(ImageButton)findViewById(R.id.imageButton);



        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preference= tv1.getText().toString();
                itemsearchsoko(preference);
            }
        });


        collectiveUploadsVarsityList = new ArrayList<>();

        DatabaseVarsity = FirebaseDatabase.getInstance().getReference("Varsity Uploads");

        DatabaseVarsity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CollectiveUploadsVarsity collectiveUploadsV = postSnapshot.getValue(CollectiveUploadsVarsity.class);
                    if(!(collectiveUploadsV.getContacturl().equals(auth.getUid()))) {
                        collectiveUploadsVarsityList.add(collectiveUploadsV);
                    }

                }
                varsityAdapter = new VarsityAdapter(collectiveUploadsVarsityList,Versityview.this);
                recyclerView.setAdapter(varsityAdapter);
                varsityAdapter.setListener(Versityview.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Versityview.this, databaseError.getMessage(),LENGTH_SHORT).show();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Versityview.this,myvarsityitemshare.class);
                startActivity(in);
            }
        });




    } public void itemsearchsoko(String preference){
        Query wanted=DatabaseVarsity.orderByChild("type").startAt(preference).endAt(preference +"\ufBff");

        wanted.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CollectiveUploadsVarsity cv = snapshot.getValue(CollectiveUploadsVarsity.class);
                    grouped=new ArrayList<>();
                    grouped.add(cv);

                }
                varsityAdapter.groupedandfiltered(grouped);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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


        Intent intent=new Intent(Versityview.this,Varsityparse.class);
        intent.putExtra(PHOTOvarsityparse,imagewanted);
        intent.putExtra(DESCvarsityparse,desc);
        intent.putExtra(TYPEvarsityparse,type);
        intent.putExtra(CONTANTvarsityparse,contact);
        intent.putExtra(PRICEvarsityparse,price);
        intent.putExtra(DURATIONvarsityparse,time);
        intent.putExtra(POSTTIMEvarsityparse,post);
        startActivity(intent);

    }
}
