package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class gowhere2 extends AppCompatActivity {

    Button press3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gowhere2);
        press3=findViewById(R.id.meet);
        SharedPreferences preferences=getSharedPreferences("PhoneBook2",MODE_PRIVATE);
        final String value1= preferences.getString("use2","" );
        press3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(gowhere2.this, "Meetings Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gowhere2.this,meetings2.class);
                intent.putExtra("give",value1);
                startActivity(intent);

            }
        });
        YoYo.with(Techniques.FlipInX).duration(3000).repeat(100).playOn(press3);
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
                Intent intent=new Intent(gowhere2.this,update.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}