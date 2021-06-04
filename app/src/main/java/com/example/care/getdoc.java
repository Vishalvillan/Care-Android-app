package com.example.care;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class getdoc extends AppCompatActivity {


    ListView docList;
    ArrayList<Person> arrayList=new ArrayList<>();
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdoc);
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

        arrayList.add(new Person("villan","Cardiologist","Time:10:00 am to 5:00pm","email : 7376villan"));
      arrayList.add(new Person("ankit","Neurologist","Time:1:00 am to 8:00pm","email : 7376ankit"));
      arrayList.add(new Person("vikas","CancerSpecialist","Time:11:00 am to 2:00pm","email : 7376vikas"));
      arrayList.add(new Person("ayush","surgeon","Time:11:00 am to 2:00pm","email : 7376ayush"));
       PersonAdapter personAdapter=new PersonAdapter(this,R.layout.list_row,arrayList);
      docList.setAdapter(personAdapter);

        docList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getdoc.this, arrayList.get(position).getName()+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getdoc.this,Appoint.class);
                String a=arrayList.get(position).getName();
                String b=arrayList.get(position).getEm();
                intent.putExtra("value1",a);
                intent.putExtra("value2",b);
                startActivity(intent);

            }
        });
    }
}