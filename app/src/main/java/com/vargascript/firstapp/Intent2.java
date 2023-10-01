package com.vargascript.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Intent2 extends AppCompatActivity {

    TextView nameId2, ageId2, colorId2;
    ConstraintLayout boxColorId;
    CheckBox checkBoxId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        Intent i = getIntent();

        ageId2 = findViewById(R.id.ageId2);
        nameId2 = findViewById(R.id.nameId2);
        colorId2 = findViewById(R.id.colorId2);
        checkBoxId2 = findViewById(R.id.checkBoxId2);
        boxColorId = findViewById(R.id.boxColorId);

        nameId2.setText(i.getStringExtra("intentName"));

        String ageText = "No se encontró nada";
        if (i.getIntExtra("intentAge", 0) != 0) {
            ageText = "Edad: " + i.getIntExtra("intentAge", 0);
        }

        ageId2.setText(ageText);
        colorId2.setText(i.getStringExtra("intentColor"));
        checkBoxId2.setChecked(i.getBooleanExtra("intentCheckBox", false));
        boxColorId.setBackgroundColor(Color.parseColor(i.getStringExtra("intentColor")));
    }
}