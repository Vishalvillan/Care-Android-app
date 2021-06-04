package com.example.care;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    EditText email,pass;
    Button prob;
    DBHelper DB;
    String v="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
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
        email=findViewById(R.id.enteremail);
        pass=findViewById(R.id.enterpass);
        getSupportActionBar().hide();
        prob=findViewById(R.id.goin);
        DB= new DBHelper(this);
        prob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=email.getText().toString();
                String userpassword=pass.getText().toString();
                if(useremail.equals("")||userpassword.equals(""))
                    Toast.makeText(signin.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass=DB.checkuserpass(useremail,userpassword);
                    if(checkuserpass==true){
                        SharedPreferences preferences=getSharedPreferences("PhoneBook",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("user",useremail);
                        editor.commit();
                        Toast.makeText(signin.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),gowhere.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(signin.this, "Invalid Credentials Please SignUp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        YoYo.with(Techniques.Pulse).duration(3000).repeat(100).playOn(prob);
    }
}