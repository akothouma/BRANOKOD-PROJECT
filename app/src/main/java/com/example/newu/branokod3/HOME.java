package com.example.newu.branokod3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HOME extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth=FirebaseAuth.getInstance();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent=new Intent(HOME.this,Login.class);
                Toast.makeText(HOME.this,"Logged out",Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navHOME);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_potesh:
                        Intent i=new Intent(HOME.this,potentialclientVarsity.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_fav:
                        Intent p=new Intent(HOME.this,wishlistVarsity.class);
                        startActivity(p);
                        return true;
                    case R.id.navigation_potesh1:
                        Intent in=new Intent(HOME.this,potentialclient.class);
                        startActivity(in);
                        return true;
                    case R.id.navigation_fav1:
                        Intent pp=new Intent(HOME.this,wishlist.class);
                        startActivity(pp);
                        return true;

                }
                return false;
            };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent zzz=new Intent(HOME.this,Profileupdate.class);
            startActivity(zzz);
            return true;
        }
        if (id == R.id.feedback) {
            Intent ppp=new Intent(HOME.this,helpdesk.class);
            startActivity(ppp);
            return true;
        }
        if (id == R.id.remove) {
          FirebaseUser user=auth.getCurrentUser();
          user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  if(task.isSuccessful()){
                      Toast.makeText(HOME.this,"Account deleted successfully",Toast.LENGTH_LONG).show();
                  }
              }
          });

            return true;
        }
        if (id == R.id.share) {
            Intent intent=new Intent(HOME.this,sharemyitems.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.additems) {
            Intent i=new Intent(HOME.this,Versityadd.class);
            startActivity(i);
        } else if (id == R.id.viewitems) {
            Intent i=new Intent(HOME.this,Versityview.class);
            startActivity(i);
        } else if (id == R.id.additems1) {
            Intent i=new Intent(HOME.this,sokoadd.class);
            startActivity(i);
        } else if (id == R.id.viewitems1) {
            Intent i=new Intent(HOME.this,sokoview.class);
            startActivity(i);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
