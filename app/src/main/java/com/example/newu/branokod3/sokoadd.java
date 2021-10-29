package com.example.newu.branokod3;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.widget.ArrayAdapter.createFromResource;

public class   sokoadd extends AppCompatActivity {
    TextView t2,t4,t12;
    ImageView im1;
    Spinner sp1,sp2;
    EditText ed1,ed2;
    Uri uri;
    TextView textView;
    public static final int PICK_IMAGE_REQUEST=1;
    DatabaseReference firebaseDatabasesoko;
    StorageReference firebaseStoragesoko;
    ProgressBar mProgressBar;
    Task<UploadTask.TaskSnapshot> notagain;
    FirebaseAuth firebaseAuth;
    int count=0;

    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sokoadd);

                t2= findViewById(R.id.sokochooseimage);
                textView= findViewById(R.id.movetoviewmysoko);
                t4= findViewById(R.id.sokocontactexchange);
                t12= findViewById(R.id.sokoupload);
                im1= findViewById(R.id.sokoimageView);
                sp2= findViewById(R.id.spinnersokotype);
                ed1= findViewById(R.id.sokodesc);
                 ed2= findViewById(R.id.sokoexpected);
                 mProgressBar =new ProgressBar(this);
                 firebaseDatabasesoko=FirebaseDatabase.getInstance().getReference("Soko Uploads");
                 firebaseStoragesoko=FirebaseStorage.getInstance().getReference("Soko Uploads");
                 firebaseAuth=FirebaseAuth.getInstance();

                        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sokoadd.this,MyItemsSoko.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter1 = createFromResource(
                sokoadd.this,R.array.Spinnertype,android.R. layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);

        t2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                             sokopickimage();
                     }
                 });

                 t12.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         {if (notagain != null ) {
                             mProgressBar.getProgress();
                             mProgressBar.isShown();
                             Toast.makeText(sokoadd.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                         } else {

                         sokoupload();
                     }
                 }
                     }
        private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
        }

        private void sokoupload() {
            final String descsoko=ed1.getText().toString().trim();
            final String expectedsoko=ed2.getText().toString().trim();
            final String ty=sp2.getSelectedItem().toString();
            final String contacturl=firebaseAuth.getUid();


            if (uri != null) {
                StorageReference storageReference = firebaseStoragesoko.child(System.currentTimeMillis()
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
                                String timestamp= String.valueOf(System.currentTimeMillis());
                                Toast.makeText(sokoadd.this, "Upload successful", Toast.LENGTH_LONG).show();
                                CollectiveUploadsSoko cs = new CollectiveUploadsSoko(descsoko,expectedsoko,contacturl,ty,timestamp,wanted);
                                String uploadId = firebaseDatabasesoko.push().getKey();
                                firebaseDatabasesoko.child(uploadId).setValue(cs);

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(sokoadd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                mProgressBar.setProgress((int) progress);
                            }
                        });
            } else {
                Toast.makeText(sokoadd.this, "No file selected", Toast.LENGTH_SHORT).show();
            }
        }
                 });
    }


     private void sokopickimage() {

             Intent intent = new Intent();
             intent.setType("image/*");
             intent.setAction(Intent.ACTION_GET_CONTENT);
             startActivityForResult(intent, PICK_IMAGE_REQUEST);
         }


     @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri=data.getData();
            Picasso.get().load(uri).fit().centerCrop().into(im1);

        }
    }

    }



