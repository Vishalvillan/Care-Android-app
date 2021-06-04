package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup2 extends AppCompatActivity {

    EditText email,pass,repass;
    TextView fb,goog;
    Button prob;
    DBHelper DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        email=findViewById(R.id.enewemail);
        pass=findViewById(R.id.enewpass);
        repass=findViewById(R.id.enewrepass);
        fb=findViewById(R.id.upfb);
        goog=findViewById(R.id.upg);
        prob=findViewById(R.id.newuser);
        DB=new DBHelper(this);
        prob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  useremail=email.getText().toString();
                String password=pass.getText().toString();
                String repassword=repass.getText().toString();
                if(useremail.equals("")||password.equals("")||repassword.equals(""))
                    Toast.makeText(signup2.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(repassword)){
                        Boolean checkuseremail=DB.checkuseremail(useremail);
                        if(checkuseremail==false){
                            Boolean insert=DB.insertData(useremail,password);
                            if(insert==true){
                                Log.d("hey","succefully inserted"+useremail);
                                Toast.makeText(signup2.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(signup2.this,signin2.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(signup2.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(signup2.this, "User Already Exist Please SignIn", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(signup2.this, "Password not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}