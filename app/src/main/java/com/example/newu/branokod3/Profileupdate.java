package com.example.newu.branokod3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AppComponentFactory;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static com.example.newu.branokod3.MyItemsSoko.TAG;

public class Profileupdate extends AppCompatActivity {
    DatabaseReference firebaseDatabaseprofile,fbb;
    StorageReference firebaseStorageprofile;
    FirebaseAuth auth;
    TextView t1;
    ImageView im1;
    Button b1;
    Uri uri;
    EditText et1;
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final String TAG = "Profileupdate";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
            Picasso.get().load(uri).fit().centerCrop().into(im1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileupdate);
        t1 = (TextView) findViewById(R.id.changephotoupdateprofile);
        im1 = (ImageView) findViewById(R.id.imageView5);
        b1 = (Button) findViewById(R.id.updateprofilebutton);
        et1 = (EditText) findViewById(R.id.contactupdateprofile);

        auth = FirebaseAuth.getInstance();
        firebaseDatabaseprofile = FirebaseDatabase.getInstance().getReference("Profile");
        fbb= firebaseDatabaseprofile = FirebaseDatabase.getInstance().getReference("Profile").child(auth.getUid());
        firebaseStorageprofile = FirebaseStorage.getInstance().getReference("Profile");


        getpreviousuri();


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpic();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatephotoandcontact();
            }
        });

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    public void getpic() {
        Intent photogetIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photogetIntent.setType("image/*");
        startActivityForResult(photogetIntent, PICK_IMAGE_REQUEST);
    }


    private void updatephotoandcontact() {
        if (uri != null) {

            StorageReference storageReference = firebaseStorageprofile.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String wanted = String.valueOf(task.getResult());
                            String nu = et1.getText().toString().trim();

                            if (TextUtils.isEmpty(nu)) {
                                Toast.makeText(Profileupdate.this, "Enter contact number", Toast.LENGTH_LONG).show();
                                return;
                            } else {

                                String userid = auth.getUid();
                                HashMap<String, Object> result = new HashMap<>();
                                result.put("uri", wanted);
                                result.put("phone", nu);
                                FirebaseDatabase.getInstance().getReference().child("Profile").child(userid).updateChildren(result);
                                Toast.makeText(Profileupdate.this, "Profile picture updated", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
                }
            });
        } else {
            Toast.makeText(Profileupdate.this, "No file selected", Toast.LENGTH_LONG).show();
            return;
        }
    }
    public void getpreviousuri(){
        fbb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Profiledetails profile=dataSnapshot.getValue(Profiledetails.class);
                String photo=profile.getUri();
                Log.d(TAG, "onDataChange: "+ photo);
                StorageReference url=FirebaseStorage.getInstance().getReferenceFromUrl(photo);
                url.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Image deleted successfully");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
