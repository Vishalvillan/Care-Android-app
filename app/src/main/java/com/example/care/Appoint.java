package com.example.care;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Appoint extends AppCompatActivity {


    Button etdate;
    Button sbtn;
    Button hospm;
    Button btn1;
    Button btn2;
    Button chat;

    DatePickerDialog.OnDateSetListener setListener;
    int t1hour,t1minute;
    public NotificationManager mNotificationManager;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint);
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
        etdate=findViewById(R.id.ehdate);
        sbtn=findViewById(R.id.docname);
        hospm=findViewById(R.id.hmap);
        btn1=findViewById(R.id.docsearch);
        btn2=findViewById(R.id.ehtime);
       // recyclerView=findViewById(R.id.recyclerView);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        etdate.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Appoint.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        etdate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Appoint.this, "The Saviors", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Appoint.this,getdoc.class);
                startActivity(intent);

            }
        });
        sbtn.setText(getIntent().getStringExtra("value1"));
        final String m=getIntent().getStringExtra("value2");
        hospm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Appoint.this, "Map", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Appoint.this,finder.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        Appoint.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1hour=hourOfDay;
                                t1minute=minute;
                                String time=t1hour+":"+t1minute;
                                SimpleDateFormat f24Hours=new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date=f24Hours.parse(time);
                                    SimpleDateFormat f12Hours=new SimpleDateFormat(
                                            "hh:mm:aa"
                                    );
                                    btn2.setText(f12Hours.format(date));
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1hour,t1minute);
                timePickerDialog.show();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opendialog();


                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(Appoint.this, "notify_001");
                Intent ii = new Intent(Appoint.this, Appoint.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(Appoint.this, 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Appointment Fixed");
                bigText.setSummaryText("On "+ etdate);

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                mBuilder.setContentTitle("Your Title");
                mBuilder.setContentText("On "+ etdate.getText().toString());
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);

                mNotificationManager =
                        (NotificationManager) Appoint.this.getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelId = "Your_channel_id";
                    NotificationChannel channel = new NotificationChannel(
                            channelId,
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_HIGH);
                    mNotificationManager.createNotificationChannel(channel);
                    mBuilder.setChannelId(channelId);
                }

                mNotificationManager.notify(0, mBuilder.build());

                SharedPreferences preferences=getSharedPreferences("PhoneBook",MODE_PRIVATE);
                String value1= preferences.getString("user","" );
                Log.d("user",value1);
                String appointment="Appointment for "+value1+" On "+etdate.getText().toString()+" to Doctor "+sbtn.getText().toString()+ " email:id "+m;
                DBHelper2 db=new DBHelper2(Appoint.this);
                Contact2 harry=new Contact2();
                harry.setName(appointment);
                db.addAppointment(harry);
                SharedPreferences preferences1=getSharedPreferences("Phone",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences1.edit();
                editor.putString("sta1",m);
                editor.putString("sta2",appointment);
                editor.commit();
                }
        });
    }
    public void opendialog()
    {
        docap docap=new docap(etdate,btn2,sbtn);
        docap.show(getSupportFragmentManager(),"dialog");

    }
}