package com.jurumuziejus.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class paslaptisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paslaptis);

        Button ButtonSuzinotiDaugiau = findViewById(R.id.button_testi);
        ButtonSuzinotiDaugiau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atidarytiDaugiau();
            }
        });
    }

    private void atidarytiDaugiau(){
        Intent intent = new Intent(this, daugiauActivity.class);
        startActivity(intent);
    }

}
