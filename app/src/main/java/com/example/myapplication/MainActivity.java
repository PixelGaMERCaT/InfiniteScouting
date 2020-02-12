package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean general, auto, teleop, misc, miscDefense, miscClimb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        general = true;
        auto = true;
        teleop = true;
        misc = true;
        miscDefense = true;
        miscClimb = true;
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        //RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //relativeLayoutParams.setMargins(16,16,16,16);
        //relativeLayout.setLayoutParams(relativeLayoutParams);

        MyScrollView myScrollView = new MyScrollView(this);
        RelativeLayout.LayoutParams myScrollViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myScrollViewParams.setMargins(16,16,16,16);
        myScrollView.setLayoutParams(myScrollViewParams);

        myScrollView.addView(LayoutInflater.from(this).inflate(R.layout.linear_layout, null));

        relativeLayout.addView(myScrollView);
        setContentView(relativeLayout);
        //setContentView(R.layout.activity_main);
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
        et.setText((val.isEmpty()) ? "1" : ""+(Integer.parseInt(val) + 1));
    }
    public void autoInnerAddFive(View view) {
        EditText et = findViewById(R.id.autoInnerHighBalls);
        String val = getFieldValue(R.id.autoInnerHighBalls);
        et.setText((val.isEmpty()) ? "5" : ""+(Integer.parseInt(val) + 5));
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
        et.setText((val.isEmpty()) ? "1" : ""+(Integer.parseInt(val) + 1));
    }
    public void autoHighAddFive(View view) {
        EditText et = findViewById(R.id.autoHighBalls);
        String val = getFieldValue(R.id.autoHighBalls);
        et.setText((val.isEmpty()) ? "5" : ""+(Integer.parseInt(val) + 5));
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
        et.setText((val.isEmpty()) ? "1" : ""+(Integer.parseInt(val) + 1));
    }
    public void autoLowAddFive(View view) {
        EditText et = findViewById(R.id.autoLowBalls);
        String val = getFieldValue(R.id.autoLowBalls);
        et.setText((val.isEmpty()) ? "5" : ""+(Integer.parseInt(val) + 5));
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
        et.setText((val.isEmpty()) ? "1" : ""+(Integer.parseInt(val) + 1));
    }
    public void teleopInnerAddFive(View view) {
        EditText et = findViewById(R.id.teleopInnerHighBalls);
        String val = getFieldValue(R.id.teleopInnerHighBalls);
        et.setText((val.isEmpty()) ? "5" : ""+(Integer.parseInt(val) + 5));
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
        et.setText((val.isEmpty()) ? "1" : ""+(Integer.parseInt(val) + 1));
    }
    public void teleopHighAddFive(View view) {
        EditText et = findViewById(R.id.teleopHighBalls);
        String val = getFieldValue(R.id.teleopHighBalls);
        et.setText((val.isEmpty()) ? "5" : ""+(Integer.parseInt(val) + 5));
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
        et.setText((val.isEmpty()) ? "1" : ""+(Integer.parseInt(val) + 1));
    }
    public void teleopLowAddFive(View view) {
        EditText et = findViewById(R.id.teleopLowBalls);
        String val = getFieldValue(R.id.teleopLowBalls);
        et.setText((val.isEmpty()) ? "5" : ""+(Integer.parseInt(val) + 5));
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
        } else {
            findViewById(R.id.name).setVisibility(View.GONE);
            findViewById(R.id.matchNumber).setVisibility(View.GONE);
            findViewById(R.id.teamNumber).setVisibility(View.GONE);
        }
    }
    public void autoView(View view) {
        if (auto = !auto) {
            findViewById(R.id.autoInnerHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.autoHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.autoLow).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.autoInnerHigh).setVisibility(View.GONE);
            findViewById(R.id.autoHigh).setVisibility(View.GONE);
            findViewById(R.id.autoLow).setVisibility(View.GONE);
        }
    }
    public void teleopView(View view) {
        if (teleop = !teleop) {
            findViewById(R.id.teleopInnerHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.teleopHigh).setVisibility(View.VISIBLE);
            findViewById(R.id.teleopLow).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.teleopInnerHigh).setVisibility(View.GONE);
            findViewById(R.id.teleopHigh).setVisibility(View.GONE);
            findViewById(R.id.teleopLow).setVisibility(View.GONE);
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


        } else {
            findViewById(R.id.miscDefenseTab).setVisibility(View.GONE);
            findViewById(R.id.miscClimbTab).setVisibility(View.GONE);
            findViewById(R.id.comments).setVisibility(View.GONE);
            findViewById(R.id.commentBox).setVisibility(View.GONE);
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
    //TODO: tabs to open/close sections

    public void submit(View view) {
        if (getFieldValue(R.id.matchNumber).equals("951")) {
            //TODO: go to admin panel
        } else if (getFieldValue(R.id.matchNumber).equals("999")) {
            //TODO: implement NFC stuff
        } else {
            general = false;
            auto = false;
            teleop = false;
            misc = false;
            generalView(null);
            autoView(null);
            teleopView(null);
            miscView(null);
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
            SeekBar defenseEffectiveness = findViewById(R.id.defenseEffectiveness);
            RadioGroup climbRadio = findViewById(R.id.climbRadio);
            EditText commentBox = findViewById(R.id.commentBox);

            if (("" + name.getText()).isEmpty() ||
                    ("" + matchNumber.getText()).isEmpty() ||
                    ("" + teamNumber.getText()).isEmpty() ||
                    climbRadio.getCheckedRadioButtonId() == -1
            ) {
                createDialog("Incomplete", "Please fill out all required fields.");
                return;
            }
            /*
            DONE check data validity
            TODO read file
            TODO append to file
            TODO write file
            DONE clear fields
             */

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
            defenseEffectiveness.setProgress(0);
            climbRadio.clearCheck();
            commentBox.setText("");



        }
    }
}
