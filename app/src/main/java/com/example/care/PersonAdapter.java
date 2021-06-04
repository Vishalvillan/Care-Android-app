package com.example.care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonAdapter extends ArrayAdapter<Person> {
    private  Context mContext;
    private int mResource;

    public PersonAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView txtName=convertView.findViewById(R.id.txtName);
        TextView txtSub=convertView.findViewById(R.id.txtSub);
        TextView txtTime=convertView.findViewById(R.id.txtTime);
        TextView txtemail=convertView.findViewById(R.id.txtemail);
        txtName.setText(getItem(position).getName());
        txtSub.setText(getItem(position).getSubTitle());
        txtTime.setText(getItem(position).getTime());
        txtemail.setText(getItem(position).getEm());
        return convertView;
    }
}
