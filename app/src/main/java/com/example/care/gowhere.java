package com.example.care;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class gowhere extends AppCompatActivity  {

    Button press1;
    Button press2;
    Button press3;
    Button press4;
    Button press5;
    Fragment press6;
  
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gowhere);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        press1=findViewById(R.id.hosp);
        press2=findViewById(R.id.clin);
        press3=findViewById(R.id.meet);
        press4=findViewById(R.id.hosp5);
        press1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(gowhere.this,  "Doctors Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gowhere.this,Appoint.class);
                startActivity(intent);
            }
        });
        press2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(gowhere.this, "Dentist Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gowhere.this,clinappoint.class);
                startActivity(intent);

            }
        });
        press4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(gowhere.this, "Dentist Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gowhere.this,detail.class);
                startActivity(intent);

            }
        });
        SharedPreferences preferences=getSharedPreferences("PhoneBook",MODE_PRIVATE);
        final String value1= preferences.getString("user","" );
        press3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(gowhere.this, "Meetings Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gowhere.this,meetings.class);
                intent.putExtra("give",value1);
                startActivity(intent);

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Update Here", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gowhere.this,update.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}