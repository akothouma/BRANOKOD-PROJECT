package com.example.newu.branokod3;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
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
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_SHORT;

public class sokoview extends AppCompatActivity implements SokoAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private SokoAdapter sokoAdapter;
    FirebaseAuth auth;
    DatabaseReference firebaseDatabasewishlist;
    TextView tv,tv1;
    public static final String PHOTOsokoparse="uri";
    public static final String DESCsokoparse ="description";
    public static final String EXPOsokoparse ="expected";
    public static final String CONTANTsokoparse="contactnumber";
    public static final String TYPEsokoparse="typeofproduct";
    public static final String DURATIONsokoparse="selltime";
    public static final String POSTtimesokoparse= "posttime";

    public static final int REQUEST=1;
    private DatabaseReference DatabaseSoko;
    ImageButton ib;
    public List<CollectiveUploadsSoko> collectiveUploadsSokoList;
    List<CollectiveUploadsSoko> grouped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sokoview);
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.my_recycler_viewsoko);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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



        collectiveUploadsSokoList = new ArrayList<>();


        DatabaseSoko = FirebaseDatabase.getInstance().getReference("Soko Uploads");
        firebaseDatabasewishlist=FirebaseDatabase.getInstance().getReference("sokowishlist");

        DatabaseSoko.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CollectiveUploadsSoko collectiveUploadsSoko = postSnapshot.getValue(CollectiveUploadsSoko.class);
                   if(!(collectiveUploadsSoko.getContact().equals(auth.getUid()))) {
                       collectiveUploadsSokoList.add(collectiveUploadsSoko);
                   }
                }
                     sokoAdapter = new SokoAdapter(collectiveUploadsSokoList, sokoview.this);
                    recyclerView.setAdapter(sokoAdapter);
                    sokoAdapter.setListener(sokoview.this);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(sokoview.this, databaseError.getMessage(), LENGTH_SHORT).show();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(sokoview.this,mysokoitemshare.class);
                startActivity(in);
            }
        });
    }
    public void itemsearchsoko(String preference){
        Query wanted=DatabaseSoko.orderByChild("type").startAt(preference).endAt(preference +"\ufBff");

        wanted.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        CollectiveUploadsSoko cs = snapshot.getValue(CollectiveUploadsSoko.class);
                    grouped=new ArrayList<>();
                    grouped.add(cs);

                    }
                    sokoAdapter.groupedandfiltered(grouped);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(sokoview.this,"Item unavailable",Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemClick(final int pos) {

        CollectiveUploadsSoko current=collectiveUploadsSokoList.get(pos);
        String imagewanted=current.getUri();
        String desc=current.getDes();
        String type=current.getType();
        String contact=current.getContact();
        String expe =current.getExpo();
        String timestamp=current.getPosttime();
        Intent intent=new Intent(sokoview.this,sokoitemviewparse.class);
        intent.putExtra(PHOTOsokoparse,imagewanted);
        intent.putExtra(DESCsokoparse,desc);
        intent.putExtra(TYPEsokoparse,type);
        intent.putExtra(CONTANTsokoparse,contact);
        intent.putExtra(EXPOsokoparse,expe);
        intent.putExtra(POSTtimesokoparse,timestamp);


        startActivity(intent);



        }
    }




