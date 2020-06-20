package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigationview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){

            case R.id.additem:
                startActivity(new Intent(getApplicationContext(),itemActivity.class));
                Toast.makeText( MainActivity.this,  "Item added",Toast.LENGTH_SHORT).show();
                break;
            case R.id.orderlist:
                startActivity(new Intent(getApplicationContext(),orderlistActivity.class));
                Toast.makeText( MainActivity.this,  "Order list is:",Toast.LENGTH_SHORT).show();
                break;
            case R.id.requestApproval:
                startActivity(new Intent(getApplicationContext(),requestapprovalActivity.class));
                Toast.makeText( MainActivity.this,  "Request are",Toast.LENGTH_SHORT).show();
                break;
            case R.id.viewitem:
                startActivity(new Intent(getApplicationContext(),viewinfoact.class));
                Toast.makeText( MainActivity.this,  "view",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                Toast.makeText( MainActivity.this,  "logout selected",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
