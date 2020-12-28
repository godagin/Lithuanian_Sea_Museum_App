package com.jurumuziejus.app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KlausimynasActivity extends AppCompatActivity {

    public static final String REIKIAMI_TASKAI = "reikiamiTaskai";

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewKlausimas;
    private TextView textViewTaskai;
    private TextView textViewKlausimuSkaiciavimas;
    private RadioGroup rbGrupe;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonPatvirtintiKitas;

    private ColorStateList textPradineSpalvaRb;

    private ArrayList<Klausimas> klausimuSarasas;
    private int klausimuSkaiciuotuvas;
    private int klausimuIsViso;
    private Klausimas dabartinisKlausimas;

    private int taskai;
    private boolean atsakytas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klausimynas);

        textViewKlausimas = findViewById(R.id.text_rodyti_klausima);
        textViewTaskai = findViewById(R.id.text_rodyti_taskus);
        textViewKlausimuSkaiciavimas = findViewById(R.id.text_rodyti_kelintas_numeris);
        rbGrupe = findViewById(R.id.radio_grupe);
        rb1 = findViewById(R.id.variantas1);
        rb2 = findViewById(R.id.variantas2);
        rb3 = findViewById(R.id.variantas3);
        buttonPatvirtintiKitas = findViewById(R.id.Button_patvirtinti_kitas);

        textPradineSpalvaRb = rb1.getTextColors();

        if(savedInstanceState == null) {
            KlausimynoDbPagalbininkas dbPagalbininkas = new KlausimynoDbPagalbininkas(this);
            klausimuSarasas = dbPagalbininkas.gautiVisusKlausimus();

            klausimuIsViso = klausimuSarasas.size();
            //Collections.shuffle(klausimuSarasas); Jeigu noreciau ne is eiles o random

            rodytiKitaKlausima();
        } else{
            klausimuSarasas = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            klausimuIsViso = klausimuSarasas.size();
            klausimuSkaiciuotuvas = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            dabartinisKlausimas = klausimuSarasas.get(klausimuSkaiciuotuvas - 1);
            taskai = savedInstanceState.getInt(KEY_SCORE);
            atsakytas = savedInstanceState.getBoolean(KEY_ANSWERED);
        }

        buttonPatvirtintiKitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!atsakytas){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        patikrintiAtsakyma();
                    }
                    else {
                        Toast.makeText(KlausimynasActivity.this, "Pasirinkite atsakymo variantą", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    rodytiKitaKlausima();
                }
            }
        });
    }

    private void  rodytiKitaKlausima(){
        rb1.setTextColor(textPradineSpalvaRb);
        rb2.setTextColor(textPradineSpalvaRb);
        rb3.setTextColor(textPradineSpalvaRb);
        rbGrupe.clearCheck();

        if (klausimuSkaiciuotuvas < klausimuIsViso){
            dabartinisKlausimas = klausimuSarasas.get(klausimuSkaiciuotuvas);

            textViewKlausimas.setText(dabartinisKlausimas.getKlausimas());
            rb1.setText(dabartinisKlausimas.getVariantas1());
            rb2.setText(dabartinisKlausimas.getVariantas2());
            rb3.setText(dabartinisKlausimas.getVariantas3());

            klausimuSkaiciuotuvas++;
            textViewKlausimuSkaiciavimas.setText("Klausimas: " + klausimuSkaiciuotuvas + "/" + klausimuIsViso);
            atsakytas = false;
            buttonPatvirtintiKitas.setText("Patvirtinti atsakymą");
        }
        else{
            pabaigtiKlausimyna();
        }
    }

    private void patikrintiAtsakyma(){
        atsakytas = true;

        RadioButton rbPasirinktas = findViewById(rbGrupe.getCheckedRadioButtonId());
        int atsakymoNr = rbGrupe.indexOfChild(rbPasirinktas) + 1;

        if(atsakymoNr == dabartinisKlausimas.getAtsakymoNr()) {
            taskai++;
            textViewTaskai.setText("Taškai: "+ taskai);
        }

        rodytiTeisinga();
    }

    private void rodytiTeisinga() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (dabartinisKlausimas.getAtsakymoNr()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewKlausimas.setText("Teisingas pirmasis atsakymas");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewKlausimas.setText("Teisingas antrasis atsakymas");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewKlausimas.setText("Teisingas trečiasis atsakymas");
                break;
        }

        if (klausimuSkaiciuotuvas < klausimuIsViso){
            buttonPatvirtintiKitas.setText("Kitas klausimas");
        }
        else{
            buttonPatvirtintiKitas.setText("Baigti");
        }

    }

    private void pabaigtiKlausimyna(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(REIKIAMI_TASKAI, taskai);
        setResult(RESULT_OK, resultIntent);
        finish();
        Intent intent = new Intent(this, BaigtiActivity.class );
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, taskai);
        outState.putInt(KEY_QUESTION_COUNT, klausimuSkaiciuotuvas);
        outState.putBoolean(KEY_ANSWERED, atsakytas);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, klausimuSarasas);
    }
}
