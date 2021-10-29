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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.widget.ArrayAdapter.createFromResource;

public class Versityadd extends AppCompatActivity {
    TextView t2,t4,t12,t13;
    ImageView im1;
    Spinner sp1,sp2;
    EditText ed1,ed2;
    Uri uri;
    public static final int PICK_IMAGE_REQUEST=1;
    DatabaseReference firebaseDatabasevarsity;
    StorageReference firebaseStoragevarsity;
    ProgressBar mProgressBar;
    Task<UploadTask.TaskSnapshot> notagain;
    FirebaseAuth firebaseAuth;



           @Override
               protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_versityadd);

               t2= findViewById(R.id.varsitypicupload);
               t4= findViewById(R.id.varsitycontactsale);
               t12= findViewById(R.id.varsityupload);
               t13= findViewById(R.id.movetoviewmyvarsity);
               im1= findViewById(R.id.varsityimageView);
               sp1= findViewById(R.id.varsityspinnerduration);
               sp2= findViewById(R.id.varsityspinnertype);
               ed1= findViewById(R.id.varsitydesc);
               ed2= findViewById(R.id.varsityprice);
               mProgressBar =new ProgressBar(this);
               firebaseDatabasevarsity= FirebaseDatabase.getInstance().getReference("Varsity Uploads");
               firebaseStoragevarsity= FirebaseStorage.getInstance().getReference("Varsity Uploads");
               firebaseAuth=FirebaseAuth.getInstance();
               t13.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                               Intent intent=new Intent(Versityadd.this,MyItemsVarsity.class);
                               startActivity(intent);
                           }
               });

               ArrayAdapter<CharSequence> adapter = createFromResource(
                       Versityadd.this,R.array.Spinnerduration,android.R.layout.simple_spinner_item);

               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               sp1.setAdapter(adapter);

               ArrayAdapter<CharSequence> adapter1 = createFromResource(
                       Versityadd.this,R.array.Spinnertype,android.R.layout.simple_spinner_item);

               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               sp2.setAdapter(adapter1);

               t2.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       varsitypickimage();
                   }
               });

               t12.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       {if (notagain != null ) {
                           mProgressBar.getProgress();
                           mProgressBar.isShown();
                           Toast.makeText(Versityadd.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                       } else {

                           varsityupload();
                       }
                       }
                   }
                   private String getFileExtension(Uri uri) {
                       ContentResolver cR = getContentResolver();
                       MimeTypeMap mime = MimeTypeMap.getSingleton();
                       return mime.getExtensionFromMimeType(cR.getType(uri));
                   }

                   private void varsityupload() {
                       final String descsoko=ed1.getText().toString().trim();
                       final String price=ed2.getText().toString().trim();
                       final String dur=sp1.getSelectedItem().toString();
                       final String ty=sp2.getSelectedItem().toString();
                       final String conturl=firebaseAuth.getUid();

                       if (uri != null) {
                           StorageReference storageReference = firebaseStoragevarsity.child(System.currentTimeMillis()
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
                                           Toast.makeText(Versityadd.this, "Upload successful", Toast.LENGTH_LONG).show();
                                           CollectiveUploadsVarsity cv = new CollectiveUploadsVarsity(descsoko,price,dur,ty,timestamp,wanted,conturl);
                                           String uploadId = firebaseDatabasevarsity.push().getKey();
                                           firebaseDatabasevarsity.child(uploadId).setValue(cv);
                                       }
                                   });
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(Versityadd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                           Toast.makeText(Versityadd.this, "No file selected", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }


    private void varsitypickimage() {
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







