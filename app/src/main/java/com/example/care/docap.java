package com.example.care;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class docap extends AppCompatDialogFragment {
    Button etdate,btn2,sbtn;
    docap(Button etdate, Button btn2, Button sbtn)
    {
     this.etdate=etdate;
     this.btn2=btn2;
     this.sbtn=sbtn;
    }
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Appointed")
                .setMessage("On "+ etdate.getText().toString()+" At "+btn2.getText().toString()+" to "+sbtn.getText().toString())
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        return builder.create();
    }
}
