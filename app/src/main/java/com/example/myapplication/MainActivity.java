package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
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

public class MainActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback {

    private boolean general, auto, teleop, misc, miscDefense, miscClimb;
    public static final String csvFilename = "data.csv";
    private String data;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        general = true;
        auto = true;
        teleop = true;
        misc = true;
        miscDefense = true;
        miscClimb = true;
        data = readCSV(this);
        super.onCreate(savedInstanceState);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        RelativeLayout relativeLayout = new RelativeLayout(this);

        MyScrollView myScrollView = new MyScrollView(this);
        RelativeLayout.LayoutParams myScrollViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myScrollViewParams.setMargins(16, 16, 16, 16);
        myScrollView.setLayoutParams(myScrollViewParams);

        myScrollView.addView(LayoutInflater.from(this).inflate(R.layout.linear_layout, null));

        relativeLayout.addView(myScrollView);
        setContentView(relativeLayout);
        if (nfcAdapter != null) {
            nfcAdapter.setNdefPushMessageCallback(this, this);
        }

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

    // button and view actions

    public void autoInnerMinusFive(View view) {
        EditText et = findViewById(R.id.autoInnerHighBalls);
        String val = getFieldValue(R.id.autoInnerHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 5 < 1) ? "" : "" + (Integer.parseInt(val) - 5));
        }
    }

    public void autoInnerMinusOne(View view) {
        EditText et = findViewById(R.id.autoInnerHighBalls);
        String val = getFieldValue(R.id.autoInnerHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 1 < 1) ? "" : "" + (Integer.parseInt(val) - 1));
        }
    }

    public void autoInnerAddOne(View view) {
        EditText et = findViewById(R.id.autoInnerHighBalls);
        String val = getFieldValue(R.id.autoInnerHighBalls);
        et.setText((val.isEmpty()) ? "1" : "" + (Integer.parseInt(val) + 1));
    }

    public void autoInnerAddFive(View view) {
        EditText et = findViewById(R.id.autoInnerHighBalls);
        String val = getFieldValue(R.id.autoInnerHighBalls);
        et.setText((val.isEmpty()) ? "5" : "" + (Integer.parseInt(val) + 5));
    }

    public void autoHighMinusFive(View view) {
        EditText et = findViewById(R.id.autoHighBalls);
        String val = getFieldValue(R.id.autoHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 5 < 1) ? "" : "" + (Integer.parseInt(val) - 5));
        }
    }

    public void autoHighMinusOne(View view) {
        EditText et = findViewById(R.id.autoHighBalls);
        String val = getFieldValue(R.id.autoHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 1 < 1) ? "" : "" + (Integer.parseInt(val) - 1));
        }
    }

    public void autoHighAddOne(View view) {
        EditText et = findViewById(R.id.autoHighBalls);
        String val = getFieldValue(R.id.autoHighBalls);
        et.setText((val.isEmpty()) ? "1" : "" + (Integer.parseInt(val) + 1));
    }

    public void autoHighAddFive(View view) {
        EditText et = findViewById(R.id.autoHighBalls);
        String val = getFieldValue(R.id.autoHighBalls);
        et.setText((val.isEmpty()) ? "5" : "" + (Integer.parseInt(val) + 5));
    }

    public void autoLowMinusFive(View view) {
        EditText et = findViewById(R.id.autoLowBalls);
        String val = getFieldValue(R.id.autoLowBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 5 < 1) ? "" : "" + (Integer.parseInt(val) - 5));
        }
    }

    public void autoLowMinusOne(View view) {
        EditText et = findViewById(R.id.autoLowBalls);
        String val = getFieldValue(R.id.autoLowBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 1 < 1) ? "" : "" + (Integer.parseInt(val) - 1));
        }
    }

    public void autoLowAddOne(View view) {
        EditText et = findViewById(R.id.autoLowBalls);
        String val = getFieldValue(R.id.autoLowBalls);
        et.setText((val.isEmpty()) ? "1" : "" + (Integer.parseInt(val) + 1));
    }

    public void autoLowAddFive(View view) {
        EditText et = findViewById(R.id.autoLowBalls);
        String val = getFieldValue(R.id.autoLowBalls);
        et.setText((val.isEmpty()) ? "5" : "" + (Integer.parseInt(val) + 5));
    }

    public void teleopInnerMinusFive(View view) {
        EditText et = findViewById(R.id.teleopInnerHighBalls);
        String val = getFieldValue(R.id.teleopInnerHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 5 < 1) ? "" : "" + (Integer.parseInt(val) - 5));
        }
    }

    public void teleopInnerMinusOne(View view) {
        EditText et = findViewById(R.id.teleopInnerHighBalls);
        String val = getFieldValue(R.id.teleopInnerHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 1 < 1) ? "" : "" + (Integer.parseInt(val) - 1));
        }
    }

    public void teleopInnerAddOne(View view) {
        EditText et = findViewById(R.id.teleopInnerHighBalls);
        String val = getFieldValue(R.id.teleopInnerHighBalls);
        et.setText((val.isEmpty()) ? "1" : "" + (Integer.parseInt(val) + 1));
    }

    public void teleopInnerAddFive(View view) {
        EditText et = findViewById(R.id.teleopInnerHighBalls);
        String val = getFieldValue(R.id.teleopInnerHighBalls);
        et.setText((val.isEmpty()) ? "5" : "" + (Integer.parseInt(val) + 5));
    }

    public void teleopHighMinusFive(View view) {
        EditText et = findViewById(R.id.teleopHighBalls);
        String val = getFieldValue(R.id.teleopHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 5 < 1) ? "" : "" + (Integer.parseInt(val) - 5));
        }
    }

    public void teleopHighMinusOne(View view) {
        EditText et = findViewById(R.id.teleopHighBalls);
        String val = getFieldValue(R.id.teleopHighBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 1 < 1) ? "" : "" + (Integer.parseInt(val) - 1));
        }
    }

    public void teleopHighAddOne(View view) {
        EditText et = findViewById(R.id.teleopHighBalls);
        String val = getFieldValue(R.id.teleopHighBalls);
        et.setText((val.isEmpty()) ? "1" : "" + (Integer.parseInt(val) + 1));
    }

    public void teleopHighAddFive(View view) {
        EditText et = findViewById(R.id.teleopHighBalls);
        String val = getFieldValue(R.id.teleopHighBalls);
        et.setText((val.isEmpty()) ? "5" : "" + (Integer.parseInt(val) + 5));
    }

    public void teleopLowMinusFive(View view) {
        EditText et = findViewById(R.id.teleopLowBalls);
        String val = getFieldValue(R.id.teleopLowBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 5 < 1) ? "" : "" + (Integer.parseInt(val) - 5));
        }
    }

    public void teleopLowMinusOne(View view) {
        EditText et = findViewById(R.id.teleopLowBalls);
        String val = getFieldValue(R.id.teleopLowBalls);
        if (!val.isEmpty()) {
            et.setText((Integer.parseInt(val) - 1 < 1) ? "" : "" + (Integer.parseInt(val) - 1));
        }
    }

    public void teleopLowAddOne(View view) {
        EditText et = findViewById(R.id.teleopLowBalls);
        String val = getFieldValue(R.id.teleopLowBalls);
        et.setText((val.isEmpty()) ? "1" : "" + (Integer.parseInt(val) + 1));
    }

    public void teleopLowAddFive(View view) {
        EditText et = findViewById(R.id.teleopLowBalls);
        String val = getFieldValue(R.id.teleopLowBalls);
        et.setText((val.isEmpty()) ? "5" : "" + (Integer.parseInt(val) + 5));
    }

    public void defenseView(View view) {
        Switch s = findViewById(R.id.defensePlayed);
        if (s.isChecked()) {
            findViewById(R.id.defenseEffectivenessQuestion).setVisibility(View.VISIBLE);
            findViewById(R.id.defenseEffectiveness).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.defenseEffectivenessQuestion).setVisibility(View.GONE);
            findViewById(R.id.defenseEffectiveness).setVisibility(View.GONE);
        }

    }

    public void generalView(View view) {
        if (general = !general) {
            findViewById(R.id.name).setVisibility(View.VISIBLE);
            findViewById(R.id.matchNumber).setVisibility(View.VISIBLE);
            findViewById(R.id.teamNumber).setVisibility(View.VISIBLE);
            findViewById(R.id.initiationCrossed).setVisibility(View.VISIBLE);
            ((TextView) view).setText(R.string.general_open);

        } else {
            findViewById(R.id.name).setVisibility(View.GONE);
            findViewById(R.id.matchNumber).setVisibility(View.GONE);
            findViewById(R.id.teamNumber).setVisibility(View.GONE);
            findViewById(R.id.initiationCrossed).setVisibility(View.GONE);
            ((TextView) view).setText(R.string.general_closed);

        }
    }

    public void autoView(View view) {
        if (auto = !auto) {
            findViewById(R.id.autoInnerHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.autoHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.autoLow).setVisibility(View.VISIBLE);
            ((TextView) view).setText(R.string.autonomous_balls_scored_open);

        } else {
            findViewById(R.id.autoInnerHigh).setVisibility(View.GONE);
            findViewById(R.id.autoHigh).setVisibility(View.GONE);
            findViewById(R.id.autoLow).setVisibility(View.GONE);
            ((TextView) view).setText(R.string.autonomous_balls_scored_closed);

        }
    }

    public void teleopView(View view) {
        if (teleop = !teleop) {
            findViewById(R.id.teleopInnerHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.teleopHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.teleopLow).setVisibility(View.VISIBLE);
            ((TextView) view).setText(R.string.teleop_balls_scored_open);

        } else {
            findViewById(R.id.teleopInnerHigh).setVisibility(View.GONE);
            findViewById(R.id.teleopHigh).setVisibility(View.GONE);
            findViewById(R.id.teleopLow).setVisibility(View.GONE);
            ((TextView) view).setText(R.string.teleop_balls_scored_closed);

        }
    }

    public void miscView(View view) {
        miscDefense = misc;
        miscClimb = misc;
        miscDefenseView(null);
        miscClimbView(null);
        if (misc = !misc) {
            findViewById(R.id.miscDefenseTab).setVisibility(View.VISIBLE);
            findViewById(R.id.miscClimbTab).setVisibility(View.VISIBLE);
            findViewById(R.id.comments).setVisibility(View.VISIBLE);
            findViewById(R.id.commentBox).setVisibility(View.VISIBLE);
            ((TextView) view).setText(R.string.misc_open);
        } else {
            findViewById(R.id.miscDefenseTab).setVisibility(View.GONE);
            findViewById(R.id.miscClimbTab).setVisibility(View.GONE);
            findViewById(R.id.comments).setVisibility(View.GONE);
            findViewById(R.id.commentBox).setVisibility(View.GONE);
            ((TextView) view).setText(R.string.misc_closed);
        }
    }

    public void miscDefenseView(View view) {
        if (miscDefense = !miscDefense) {
            findViewById(R.id.defensePlayed).setVisibility(View.VISIBLE);
            defenseView(null);
            findViewById(R.id.defensePlayedAgainst).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.defensePlayed).setVisibility(View.GONE);
            findViewById(R.id.defenseEffectivenessQuestion).setVisibility(View.GONE);
            findViewById(R.id.defenseEffectiveness).setVisibility(View.GONE);
            findViewById(R.id.defensePlayedAgainst).setVisibility(View.GONE);
        }
    }

    public void miscClimbView(View view) {
        if (miscClimb = !miscClimb) {
            findViewById(R.id.climbRadio).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.climbRadio).setVisibility(View.GONE);
        }
    }

//    public void onResume() {
//        super.onResume();
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
//    }
//
//    public void onPause() {
//        super.onPause();
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        nfcAdapter.disableForegroundDispatch(this);
//    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        NdefRecord[] record = {new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], readCSV(this).getBytes())};
        return new NdefMessage(record);
    }

    public void submit(View view) {
        if (getFieldValue(R.id.matchNumber).equals("9731")) {
            Intent intent = new Intent(getApplicationContext(), AdminPanelActivity.class);
            startActivity(intent);
        } else if (getFieldValue(R.id.matchNumber).equals("1379")) {
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
                writer.append(readCSV(this));
                writer.flush();
                writer.close();
                Toast.makeText(this, "File saved to " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            general = false;
            auto = false;
            teleop = false;
            misc = false;
            generalView(findViewById(R.id.generalTab));
            autoView(findViewById(R.id.autoTab));
            teleopView(findViewById(R.id.teleopTab));
            miscView(findViewById(R.id.miscTab));
            EditText name = findViewById(R.id.name);
            EditText matchNumber = findViewById(R.id.matchNumber);
            EditText teamNumber = findViewById(R.id.teamNumber);
            EditText autoInnerBalls = findViewById(R.id.autoInnerHighBalls);
            EditText autoHighBalls = findViewById(R.id.autoHighBalls);
            EditText autoLowBalls = findViewById(R.id.autoLowBalls);
            EditText teleopInnerBalls = findViewById(R.id.teleopInnerHighBalls);
            EditText teleopHighBalls = findViewById(R.id.teleopHighBalls);
            EditText teleopLowBalls = findViewById(R.id.teleopLowBalls);
            Switch defensePlayed = findViewById(R.id.defensePlayed);
            Switch defensePlayedAgainst = findViewById(R.id.defensePlayedAgainst);
            Switch initiationCrossed = findViewById(R.id.initiationCrossed);
            SeekBar defenseEffectiveness = findViewById(R.id.defenseEffectiveness);
            RadioGroup climbRadio = findViewById(R.id.climbRadio);
            EditText commentBox = findViewById(R.id.commentBox);

            if (("" + name.getText()).isEmpty() ||
                    ("" + matchNumber.getText()).isEmpty() ||
                    ("" + teamNumber.getText()).isEmpty() ||
                    climbRadio.getCheckedRadioButtonId() == -1) {
                createDialog("Incomplete", "Please fill out all required fields.");
                return;
            }
            String contents = readCSV(this);
            //write to file
            contents += getFieldValue(R.id.name) + "," +getFieldValue(R.id.matchNumber) + "," + getFieldValue(R.id.teamNumber) + ",";
            contents += (((initiationCrossed.isChecked()))) ? "true," : "false,";
            contents += getFieldValue(R.id.autoInnerHighBalls) + "," +getFieldValue(R.id.autoHighBalls) + "," +getFieldValue(R.id.autoLowBalls) + "," +getFieldValue(R.id.teleopInnerHighBalls) + "," +getFieldValue(R.id.teleopHighBalls) + "," +getFieldValue(R.id.teleopLowBalls) + ",";
            boolean defPlayed = (defensePlayed).isChecked();
            if (defPlayed) {
                contents += "true," + (defenseEffectiveness).getProgress() + ",";
            } else {
                contents += "false,N/A,";
            }
            contents += (((defensePlayedAgainst).isChecked())) ? "true," : "false,";
            switch (((RadioGroup) findViewById(R.id.climbRadio)).getCheckedRadioButtonId()) {
                case R.id.climb1:
                    contents += "1";
                    break;
                case R.id.climb2:
                    contents += "2";
                    break;
//                case R.id.climb3:
//                    contents += "3";
//                    break;
//                case R.id.climb4:
//                    contents += "4";
//                    break;
                case R.id.climb5:
                    contents += "3";
                    break;
            }
            writeCSV(this, contents);
            matchNumber.setText("" + (Integer.parseInt("" + matchNumber.getText()) + 1));
            teamNumber.setText("");
            autoInnerBalls.setText("");
            autoHighBalls.setText("");
            autoLowBalls.setText("");
            teleopInnerBalls.setText("");
            teleopHighBalls.setText("");
            teleopLowBalls.setText("");
            defensePlayed.setChecked(false);
            defenseView(null);
            defensePlayedAgainst.setChecked(false);
            initiationCrossed.setChecked(false);
            defenseEffectiveness.setProgress(0);
            climbRadio.clearCheck();
            commentBox.setText("");
            Toast.makeText(this, "Successfully saved.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(null);
//        ((TextView) findViewById(R.id.outBox)).setText(intent.getAction());
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] receivedArray = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (receivedArray != null) {
                data += new String(((NdefMessage) receivedArray[0]).getRecords()[0].getPayload());
                writeCSV(this, data);
            }
        }
    }
    public static void writeCSV(Context c, String contents) {
        try {
            FileOutputStream fos = c.openFileOutput(csvFilename, Context.MODE_PRIVATE);
            fos.write(contents.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readCSV(Context c) {
        String contents = "";
        try {
            FileInputStream fis = c.openFileInput(csvFilename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            contents = stringBuilder.toString();
            fis.close();
            inputStreamReader.close();
        } catch (FileNotFoundException e) {
            // make a file if it doesn't exist
            File data = new File(c.getFilesDir(), csvFilename);
            try {
                data.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

}
