package com.example.newu.branokod3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class helpdesk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesk);

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","branokodhelp@gmail.com", null));
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }
}
