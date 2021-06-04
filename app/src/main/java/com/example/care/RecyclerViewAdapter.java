package com.example.care;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    @NonNull

    private Context context;
    private List<Contact2> contactList;

    @NonNull
    @Override
    ////what to show anything again n again
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //once getviewholder object  what to do
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact2 contact2=contactList.get(position);

        holder.contactName.setText(contact2.getName());
    }

    @Override
    //how many items
    public int getItemCount() {
        return contactList.size();
    }

    public RecyclerViewAdapter(@NonNull Context context, List<Contact2> contactList) {
        this.context = context;
        this.contactList = contactList;
    }
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView contactName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            contactName=itemView.findViewById(R.id.name);
        }

        @Override
        public void onClick(View v) {
            int position=this.getAdapterPosition();
            Contact2 contact2=contactList.get(position);
            String name=contact2.getName();
            Toast.makeText(context,  "Name is:" +name, Toast.LENGTH_SHORT).show();

        }
    }
}