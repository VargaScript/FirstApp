package com.vargascript.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView greeting;
    Button explicitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greeting = findViewById(R.id.greetingTxt);

        Log.d("hola_mundo", "Start onCreate");

        explicitButton = findViewById(R.id.explicitButton);

        //Evento click

        explicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Integración de Intent Explícito
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
                //finish()  Se utiliza para destruir la actividad en contexto
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hola_mundo", "Start onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("hola_mundo", "Start onResume");
        greeting.setText("Hola pinches perras");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hola_mundo", "Start onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("hola_mundo", "Start onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("hola_mundo", "Start onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("hola_mundo", "Start onRestart");
    }
}