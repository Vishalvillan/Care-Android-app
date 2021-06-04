package com.example.care;

import android.content.Intent;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button pat, sta;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        pat=findViewById(R.id.PATIENTS);
        sta=findViewById(R.id.STAFFS);
        pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Map", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,user.class);
                startActivity(intent);
            }
        });
        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Map", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,staff.class);
                startActivity(intent);
            }
        });

            YoYo.with(Techniques.Pulse).duration(3000).repeat(100).playOn(pat);
            YoYo.with(Techniques.Pulse).duration(3000).repeat(100).playOn(sta);
    }
}