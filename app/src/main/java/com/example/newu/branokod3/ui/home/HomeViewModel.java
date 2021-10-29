package com.example.newu.branokod3.ui.home;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class HomeViewModel extends ViewModel {

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

    public HomeViewModel() {
    }
}