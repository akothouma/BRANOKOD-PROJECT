package com.example.newu.branokod3;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static com.example.newu.branokod3.CompleteRegistration.EMAIL;
import static com.example.newu.branokod3.CompleteRegistration.Password;

public class Profile extends AppCompatActivity {
    TextView  profilepictureselect, profileupload;
    ImageView imageView;
    Button b7;
    EditText et20, et21, et22;
    public static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mProgressBar;
    StorageReference firebaseStorageprofile;
    DatabaseReference firebaseDatabaseprofile;
    Uri uri;
    Task<UploadTask.TaskSnapshot> notagain;
FirebaseAuth firebaseAuth;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
            Picasso.get().load(uri).fit().centerCrop().into(imageView);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        b7 = findViewById(R.id.ButtonS);
        profilepictureselect = findViewById(R.id.dp);
        imageView = findViewById(R.id.imageViewPicture);
        et20 = findViewById(R.id.editText20);
        mProgressBar = new ProgressBar(this);
        et21 = findViewById(R.id.editText21);
        et22 = findViewById(R.id.editText22);
        firebaseDatabaseprofile = FirebaseDatabase.getInstance().getReference("Profile");
        firebaseStorageprofile = FirebaseStorage.getInstance().getReference("Profile");
        firebaseAuth = FirebaseAuth.getInstance();


        profilepictureselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photogetIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photogetIntent.setType("image/*");
                startActivityForResult(photogetIntent, PICK_IMAGE_REQUEST);
            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notagain != null) {
                    mProgressBar.getProgress();
                    mProgressBar.isShown();
                    Toast.makeText(Profile.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    profileUpload();
                }
            }
        });




        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }



    private void profileUpload() {

        final String registrationnum = et21.getText().toString().trim();
        final String Campus = et20.getText().toString().trim();
        final String Contact = et22.getText().toString().trim();
        if (TextUtils.isEmpty(registrationnum)) {
            Toast.makeText(Profile.this, "Please input your student registration number", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Campus)) {
            Toast.makeText(Profile.this, "Please select your Institution and campus", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Contact)) {
            Toast.makeText(Profile.this, "Please select your phone number", Toast.LENGTH_LONG).show();
            return;
        }
        else {

            if (uri != null) {
                StorageReference storageReference = firebaseStorageprofile.child(System.currentTimeMillis()
                        + "." + getFileExtension(uri));
                notagain = storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(0);
                            }
                        }, 500);
                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String wanted= String.valueOf(task.getResult());
                                Profiledetails pd = new Profiledetails(registrationnum, Campus, Contact,wanted);
                                String uploadId = firebaseAuth.getUid();
                                firebaseDatabaseprofile.child(uploadId).setValue(pd);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                mProgressBar.setProgress((int) progress);
                            }
                        });
                Intent in=getIntent();
                String email1=in.getStringExtra(EMAIL);
                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            Toast.makeText(Profile.this, "Verification email sent to your account", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Profile.this, "Authentication failed" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Toast.makeText(Profile.this, "No file selected", Toast.LENGTH_SHORT).show();
            }
        }

    }
}








