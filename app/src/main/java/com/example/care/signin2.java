package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class signin2 extends AppCompatActivity {

    EditText email,pass;
    Button prob;
    DBHelper DB;
    String v="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin2);
        email=findViewById(R.id.enteremail);
        pass=findViewById(R.id.enterpass);
       // getSupportActionBar().hide();
        prob=findViewById(R.id.goin);
        DB= new DBHelper(this);
        prob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=email.getText().toString();
                String userpassword=pass.getText().toString();
                if(useremail.equals("")||userpassword.equals(""))
                    Toast.makeText(signin2.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass=DB.checkuserpass(useremail,userpassword);
                    if(checkuserpass==true){
                        SharedPreferences preferences=getSharedPreferences("PhoneBook2",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("use2",useremail);
                        editor.commit();
                        Toast.makeText(signin2.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(signin2.this,gowhere2.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(signin2.this, "Invalid Credentials Please SignUp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        YoYo.with(Techniques.Pulse).duration(3000).repeat(100).playOn(prob);
    }
}