 package com.example.newu.branokod3;

 import android.content.Intent;
 import android.os.Bundle;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import android.text.TextUtils;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.FirebaseAuth;

 public class Forgotpassword extends AppCompatActivity {
EditText et6;
TextView rpe;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        et6= findViewById(R.id.recoveryemailforpassword);
        rpe= findViewById(R.id.sendrecoveryemailforpassword);
        auth=FirebaseAuth.getInstance();


        rpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailpresent();

            }
        });
    }

    public void emailpresent(){
        String email=et6.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_LONG).show();
        }
          auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Forgotpassword.this,"Password reset email sent ",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
