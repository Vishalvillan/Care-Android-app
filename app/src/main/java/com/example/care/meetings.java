package com.example.care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class meetings extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private  ArrayList<Contact2> contactArrayList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactArrayList=new ArrayList<>();
        DBHelper2 dbHelper2=new DBHelper2(this);
        Intent intent=getIntent();
        String value1=intent.getStringExtra("give");
        Log.d("use",value1);
        List<Contact2> contactList= dbHelper2.getAllContacts(value1);
        for (Contact2 contact2:contactList)
        {
            contactArrayList.add(contact2);
        }
        Collections.reverse(contactArrayList);
        recyclerViewAdapter=new RecyclerViewAdapter(meetings.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}