package com.example.newu.branokod3;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    Button b2;
    TextView tv;
    EditText et7, et8, et9, et10;
    FirebaseAuth auth;
    DatabaseReference firebaseDatabase;


    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(Login.this,NAVIGATE.class);
            startActivity(intent);

        } else {
            Toast.makeText(Login.this, "You are signed out.Login to proceed",
                    Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        b2 = findViewById(R.id.loginbutton);
        tv = findViewById(R.id.textView4);
        et9 = findViewById(R.id.Password);
        et10 = findViewById(R.id.emaillogin);
        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Profile");


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickuserinfo();


            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Forgotpassword.class);
                startActivity(intent);
            }
        });


    }

    public void pickuserinfo() {


        String email = et10.getText().toString().trim();
        String password = et9.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final FirebaseUser user = auth.getCurrentUser();
                    Intent intent = new Intent(Login.this, HOME.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Login.this, "Enter the right credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}





