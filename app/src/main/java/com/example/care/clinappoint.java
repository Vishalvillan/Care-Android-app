package com.example.care;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.care.R.*;

public class clinappoint extends AppCompatActivity {

    Button etdate;
    Button sbtn;
    Button clinm;
    Button btn1;
    DatePickerDialog.OnDateSetListener setListener;
    Button btn2;
    int t1hour,t1minute;
    private AlertDialog.Builder dialogBuilder;
    private  AlertDialog dialog;
    private EditText firstname,addname,phonenumber;
    private Button savebtn,cancelbtn;
    public NotificationManager mNotificationManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_clinappoint);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        etdate=findViewById(id.ecdate);
        sbtn=findViewById(id.denname);
        btn2=findViewById(id.edtime);
        clinm=findViewById(id.cmap);
        btn1=findViewById(id.densearch);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        clinappoint.this, new DatePickerDialog.OnDateSetListener() {
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
                Toast.makeText(clinappoint.this, "The Saviors", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(clinappoint.this,getden.class);
                startActivity(intent);
            }
        });
        sbtn.setText(getIntent().getStringExtra("value1"));
        clinm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(clinappoint.this, "Map", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(clinappoint.this,finder.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        clinappoint.this,
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
                        new NotificationCompat.Builder(clinappoint.this, "notify_001");
                Intent ii = new Intent(clinappoint.this, Appoint.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(clinappoint.this, 0, ii, 0);

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
                        (NotificationManager) clinappoint.this.getSystemService(Context.NOTIFICATION_SERVICE);

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
                String appointment="Appointment for "+value1+" On "+etdate.getText().toString()+" to Doctor "+sbtn.getText().toString();
                DBHelper2 db=new DBHelper2(clinappoint.this);
                Contact2 harry=new Contact2();
                harry.setName(appointment);
                db.addAppointment(harry);
            }
        });
    }
    public void opendialog()
    {
        docap docap=new docap(etdate,btn2,sbtn);
        docap.show(getSupportFragmentManager(),"dialog");
    }
    public void createNewContactDialog()
    {
        dialogBuilder=new AlertDialog.Builder(this);
        final View contactPopup= getLayoutInflater().inflate(layout.popup,null);
        firstname=contactPopup.findViewById(id.firstname);
        addname=contactPopup.findViewById(id.addname);
        phonenumber=contactPopup.findViewById(id.phoneno);
        savebtn=contactPopup.findViewById(id.savebtn);
        savebtn=contactPopup.findViewById(id.savebtn);
        cancelbtn=contactPopup.findViewById(id.cancellbtn);
        dialogBuilder.setView(contactPopup);
        dialog=dialogBuilder.create();
        dialog.show();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(clinappoint.this,clinappoint.class);
                startActivity(intent);
            }
        });
    }
}