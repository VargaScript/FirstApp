package com.vargascript.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button implicitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        implicitButton = findViewById(R.id.implicitButton);

        implicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                Uri videoUrl = Uri.parse("https://www.youtube.com/watch?v=Bm5iA4Zupek");
                i.setData(videoUrl);

                startActivity(i);
            }
        });
    }
}