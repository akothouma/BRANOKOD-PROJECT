package com.example.newu.branokod3;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CompleteRegistration extends AppCompatActivity {
    TextView completeregistration;
    EditText et4, et5, et2;
    private FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    public static String EMAIL = "user email";
    public static String Password = "user password";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_registration);

        progressDialog = new ProgressDialog(CompleteRegistration.this);
        firebaseAuth = FirebaseAuth.getInstance();
        completeregistration = findViewById(R.id.profileget);
        et4 = findViewById(R.id.editText4);
        et5 = findViewById(R.id.editText5);
        et2 = findViewById(R.id.editText2);

        progressDialog = new ProgressDialog(CompleteRegistration.this);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("StudentsDetails");


        completeregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();

            }
        });

    }


    public void registeruser() {


        final String email = et4.getText().toString().trim();
        final String yoc = et2.getText().toString().trim();
        final String password = et5.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(CompleteRegistration.this, "Please enter your email address", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(CompleteRegistration.this, "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(yoc)) {
            Toast.makeText(CompleteRegistration.this, "Please enter your year of graduation", Toast.LENGTH_LONG).show();
            return;
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        StudentDetails studentDetails = new StudentDetails(email, yoc);
                        String StudentId = firebaseAuth.getUid();
                        firebaseDatabase.child(StudentId).setValue(studentDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(CompleteRegistration.this, Profile.class);
                                    intent.putExtra(EMAIL, email);
                                    intent.putExtra(Password, password);
                                    startActivity(intent);
                                }
                            }

                        });
                    } else {
                        Toast.makeText(CompleteRegistration.this, "Account with related email exists", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}