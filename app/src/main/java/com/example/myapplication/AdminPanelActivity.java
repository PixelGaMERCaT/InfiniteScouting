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

//    private boolean nfcRunning;
    private String data;
    private NfcAdapter nfcAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        nfcRunning = false;
        setContentView(R.layout.admin_panel);
        data = MainActivity.readCSV(this);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            nfcAdapter.setNdefPushMessage(null, this);
        }

    }

    @Override
    public void onNewIntent(Intent nfcIntent) {
        super.onNewIntent(null);
        Toast.makeText(this, "received", Toast.LENGTH_SHORT).show();

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(nfcIntent.getAction())) {
            Parcelable[] receivedArray = nfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (receivedArray != null) {
                data += new String(((NdefMessage) receivedArray[0]).getRecords()[0].getPayload());
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            }
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
//            Toast.makeText(this, ""+f., Toast.LENGTH_SHORT).show();
            FileWriter writer = new FileWriter(f);
            writer.append(data);
            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved to " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();

        }

//        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("text/csv");
//        intent.putExtra(Intent.EXTRA_TITLE, "export.csv");
//        startActivityForResult(intent, 1);

        //DONE: Export all data as movable file
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
//        super.onActivityResult(requestCode, resultCode, resultData);
//        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
//            // The result data contains a URI for the document or directory that
//            // the user selected.
//            Uri uri;
//            if (resultData != null) {
//                uri = resultData.getData();
//                try {
//                    ParcelFileDescriptor pfd = this.getContentResolver().openFileDescriptor(uri, "w");
//                    FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor());
//                    fileOutputStream.write(data.getBytes());
//                    // Let the document provider know you're done by closing the stream.
//                    fileOutputStream.close();
//                    pfd.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


}
