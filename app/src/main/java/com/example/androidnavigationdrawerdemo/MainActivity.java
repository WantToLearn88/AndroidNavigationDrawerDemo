package com.example.androidnavigationdrawerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        // Step-1

        setSupportActionBar(toolbar);

        // Step-2

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);

        // Step-3
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Step-4
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();

               if(id == R.id.optNotes)
               {
                    loadFragment(new NotesFragment(), true);
               }
               else if(id == R.id.optHome)
               {
                    loadFragment(new HomeFragment(), false);
               }
               else
               {
                    loadFragment(new SettingFragment(), false);
               }

               drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }


    private void loadFragment(Fragment fragment, boolean flag)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag) {
            ft.add(R.id.container, fragment);
        }
        else {
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}