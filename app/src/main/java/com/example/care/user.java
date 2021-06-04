package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class user extends AppCompatActivity {

    Button login, sup;
    LinearLayout ll;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        login=findViewById(R.id.btnlogin);
        sup=findViewById(R.id.btnsignup);
        ll=findViewById(R.id.ll);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(user.this, "Login has been clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(user.this,signin.class);
                startActivity(intent);
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(user.this, "SignUp has been clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(user.this,signup.class);
                startActivity(intent);
            }
        });
        YoYo.with(Techniques.SlideInRight).duration(3000).repeat(0).playOn(login);
        YoYo.with(Techniques.SlideInLeft).duration(3000).repeat(0).playOn(sup);
    }
}