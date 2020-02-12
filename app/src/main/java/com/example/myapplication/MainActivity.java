package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
        builder.create();

    }

    private String getFieldValue(int id) {
        return (((EditText) findViewById(id)).getText()).toString();
    }

    public void submit(View view) {
        if (getFieldValue(R.id.matchNumber).equals("951")) {
            //TODO: go to admin panel
        } else if (getFieldValue(R.id.matchNumber).equals("999")) {
            //TODO: implement NFC stuff
        } else {
            //TODO: save data
            /*
            read file
            append to file
            write file
            clear fields
             */
        }
    }
}
