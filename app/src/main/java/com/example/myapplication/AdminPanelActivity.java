package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AdminPanelActivity extends AppCompatActivity {

    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        data = MainActivity.readCSV(this);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            nfcAdapter.setNdefPushMessage(null, this);
        }

    }

    public void deleteCSV(View view) {
        MainActivity.writeCSV(this, "");
    }

    public void showCSV(View view) {
        ((TextView) findViewById(R.id.outBox)).setText(data);
    }

    public void export(View view) {
        TextView tv = findViewById(R.id.outBox);
        tv.setText(data);

        if (Build.VERSION.SDK_INT > 23) {
            // Check whether this app has write external storage permission or not.
            int writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            // If do not grant write external storage permission.
            if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                // Request user to grant write external storage permission.
                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
            }
        }
        try {
            File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "export.csv");
            FileWriter writer = new FileWriter(f);
            writer.append(data);
            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved to " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}