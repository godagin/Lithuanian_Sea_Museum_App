package com.jurumuziejus.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaigtiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baigti);

        Button ButtonIPradzia = findViewById(R.id.button_i_pradzia);
        ButtonIPradzia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eitiIPradzia();
            }
        });
    }

    private void eitiIPradzia(){
        finish();
    }

}
