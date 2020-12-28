package com.jurumuziejus.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PradziaActivity extends AppCompatActivity implements View.OnClickListener {


    //main quiz
    private static final int REQUEST_CODE_KLAUSIMYNAS = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";


    private int highscore;

    //paslaptis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButtonPradetiKlausimyna = findViewById(R.id.button_pradeti);
        ButtonPradetiKlausimyna.setOnClickListener(this);

        Button buttonIP = findViewById(R.id. button_paslaptis);
        buttonIP.setOnClickListener(this);
    }

    public void atidarytiPaslaptis(){
        startActivity(new Intent(this, paslaptisActivity.class));
    }

    public void pradetiKlausimyna(){
        Intent intent = new Intent(this, KlausimynasActivity.class);
        startActivityForResult(intent, REQUEST_CODE_KLAUSIMYNAS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_KLAUSIMYNAS){
            if(resultCode == RESULT_OK){
                int taskai = data.getIntExtra(KlausimynasActivity.REIKIAMI_TASKAI, 0);
                    if (taskai > highscore){
                        updateHighscore(taskai);
                    }
            }
        }
    }

    public void updateHighscore(int highscoreNew){ //isaugom highscore
        highscore = highscoreNew;

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_pradeti:
                pradetiKlausimyna();
                break;
            case R.id.button_paslaptis:
                if (highscore >= 8){ atidarytiPaslaptis();}
                else { Toast.makeText(this, "Atlik testą ir surink bent 8 taškus!", Toast.LENGTH_SHORT).show();}
                break;
        }
    }
}
