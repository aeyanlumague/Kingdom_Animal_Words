package com.example.kingdomanimalwords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Activity21 extends AppCompatActivity {
    private int presCounter = 0;
    private int maxPresCounter = 4;
    private String[] keys = {"F","R","O","G","H","Y"};
    private String textAnswer = "FROG";
    TextView textSreen, textQuestion, textTitle;
    Button nextBtn, aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity21);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity21.this, Activity22.class);
                startActivity(intent);
                MediaPlayer bgmusic = MediaPlayer.create(Activity21.this,R.raw.sparkle);
                bgmusic.start();
                finish();
            }
        });

        aboutBtn = findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog Description = new AlertDialog.Builder(Activity21.this).create();

                Description.setMessage("ANURA - have protruding eyes, no tail, and strong, webbed hind feet that are adapted for leaping and swimming. They also possess smooth, moist skins. Many are predominantly aquatic, but some live on land, in burrows, or in trees. A number depart from the typical form..");
                Description.setButton(AlertDialog.BUTTON1, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                Description.show();
            }
        });


        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        keys = shuffleArray(keys);
        for (String key : keys){
            addView((findViewById(R.id.layoutParent)), key, (findViewById(R.id.editText)));
        }
        maxPresCounter = 4;
    }

    private String[] shuffleArray(String[] ar){
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--){
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    private void addView(LinearLayout viewParent, final String text, final EditText editText){
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin =5;

        final TextView textView = new TextView(this);
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter < maxPresCounter){
                    if (presCounter == 0)
                        editText.setText("");
                    editText.setText(editText.getText().toString() + text);
                    // textView.startAnimation(bigsmallforth)
                    textView.animate().alpha(0).setDuration(100);
                    presCounter++;

                    if (presCounter == maxPresCounter)
                        doValidate();

                }
            }
        });

        viewParent.addView(textView);
    }

    private void doValidate() {
        presCounter = 0;

        EditText editText  = findViewById(R.id.editText);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);

        if(editText.getText().toString().equals(textAnswer)){
            Toast.makeText(Activity21.this, "Correct", Toast.LENGTH_SHORT).show();
            editText.setText("");
            nextBtn = findViewById(R.id.nextBtn);
            nextBtn.setVisibility(View.VISIBLE);
        }else {
            Toast.makeText(Activity21.this, "Wrong", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
        keys = shuffleArray(keys);
        linearLayout.removeAllViews();
        for (String key : keys){
            addView(linearLayout, key, editText);
        }
    }
}