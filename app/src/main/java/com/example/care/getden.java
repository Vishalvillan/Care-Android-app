package com.example.care;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class getden extends AppCompatActivity {

    ListView docList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getden);
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
        docList=findViewById(R.id.dlist);
        final ArrayList<Person> arrayList=new ArrayList<>();
        arrayList.add(new Person("villan","Cardiologist","Time:10:00 am to 5:00pm","7376villan"));
        arrayList.add(new Person("ankit","Neurologist","Time:1:00 am to 8:00pm","7376ankit"));
        arrayList.add(new Person("vikas","CancerSpecialist","Time:11:00 am to 2:00pm","7376vikas"));
        arrayList.add(new Person("ayush","surgeon","Time:11:00 am to 2:00pm","7376ayush"));
        PersonAdapter personAdapter=new PersonAdapter(this,R.layout.list_row,arrayList);
        docList.setAdapter(personAdapter);
        docList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getden.this, arrayList.get(position).getName()+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getden.this,clinappoint.class);
                String a=arrayList.get(position).getName();
                String b=arrayList.get(position).getEm();
                intent.putExtra("value1",a);
                intent.putExtra("value2",b);
                startActivity(intent);

            }
        });
    }
}