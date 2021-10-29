package com.example.newu.branokod3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.newu.branokod3.MyItemsSoko.NUM;
import static com.example.newu.branokod3.varsitypayment.varsityamounttotal;
import static java.lang.String.valueOf;

public class sokopayment extends AppCompatActivity {
    TextView t1;
    ImageButton airtel,saf;
    int total,yote;
    public static final String sokopricetotal="YASOKO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sokopayment);
        t1= findViewById(R.id.servicefeeamount);
        airtel= findViewById(R.id.imageButton3);
        saf= findViewById(R.id.imageButton4);
        Intent in=getIntent();
        String varprice=in.getStringExtra(varsityamounttotal);

        calculateprice();

        airtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(sokopayment.this,airtelpaymentmode.class);
                intent.putExtra("YASOKO",total);
                startActivity(intent);
            }
        });

        saf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(sokopayment.this,safaricompaymentmode.class);
                inte.putExtra("YASOKO",total);
                startActivity(inte);

            }
        });
    }

        int calculateprice() {
            Intent intent=getIntent();
            int howmuch=intent.getIntExtra(NUM,0);


            total=119* howmuch;
            t1.setText(valueOf(total));
            return 0;
        }


    }

