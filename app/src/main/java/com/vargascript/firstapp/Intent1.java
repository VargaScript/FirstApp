package com.vargascript.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

public class Intent1 extends AppCompatActivity {

    EditText nameId, ageId, colorId;
    CheckBox checkBoxId;
    Button buttonId;
    SharedPreferences sp;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);

        sp = this.getSharedPreferences("UserPreference", Context.MODE_PRIVATE);
        nameId = findViewById(R.id.nameId);
        ageId = findViewById(R.id.ageId);
        colorId = findViewById(R.id.colorId);
        checkBoxId = findViewById(R.id.checkBoxId);
        buttonId = findViewById(R.id.buttonId);

        //
        nameId.setText(sp.getString("intentName", ""));
        ageId.setText("" + sp.getInt("intentAge", 0));
        colorId.setText(sp.getString("intentColor", "#000000"));
        checkBoxId.setChecked(sp.getBoolean("intentCheckBox", true));

        //Evento click

        buttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Método para obtener la información
                sendInfo();
            }
        });
    }

    private void sendInfo() {

        String name, color;
        int age;
        boolean checkBox;

        //Obtener contenido de componentes
        name = nameId.getText().toString();
        color = colorId.getText().toString();
        age = Integer.parseInt(ageId.getText().toString());
        checkBox = checkBoxId.isChecked();

        // Almacenar información en SHaredPreferences
        SharedPreferences.Editor editor = sp.edit();
        if(checkBox) {
            editor.putString("intentName", name);
            editor.putString("intentColor", color);
            editor.putInt("intentAge", age);
            editor.putBoolean("intentCheckBox", checkBox);
            editor.apply();
        }else{
            editor.clear();
            editor.apply();
        }

        //Enviar información a otra pantalla
        Intent i = new Intent(Intent1.this, Intent2.class);
        i.putExtra("intentName", name);
        i.putExtra("intentAge", age);
        i.putExtra("intentColor", color);
        i.putExtra("intentCheckBox", checkBox);
        startActivity(i);
    }
}