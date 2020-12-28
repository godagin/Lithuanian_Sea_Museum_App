package com.jurumuziejus.app;

//17eilutej gali buti klaida tada reik nutrint prieki ir palikt tikcontext


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jurumuziejus.app.KlausimynoKontraktas.*;

import java.util.ArrayList;
import java.util.List;


public class KlausimynoDbPagalbininkas extends SQLiteOpenHelper {

    private static final String DATABASE_VARDAS = "JuruMuziejausKlausimynas.db";
    private static final int DATABASE_VERSIJA = 9;

    private SQLiteDatabase db;

    public KlausimynoDbPagalbininkas( Context context) {

        super(context, DATABASE_VARDAS, null, DATABASE_VERSIJA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_SUKURTI_KLAUSIMU_LENTELE = "CREATE TABLE " +
                KlausimuLentele.LENTELES_VARDAS + " ( " +
                KlausimuLentele._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KlausimuLentele.STULPELIO_KLAUSIMAS + " TEXT, " +
                KlausimuLentele.STULPELIO_VARIANTAS1 + " TEXT, " +
                KlausimuLentele.STULPELIO_VARIANTAS2 + " TEXT, " +
                KlausimuLentele.STULPELIO_VARIANTAS3 + " TEXT, " +
                KlausimuLentele.STULPELIO_ATSAKYMO_NR + " INTEGER" + ")";

        db.execSQL(SQL_SUKURTI_KLAUSIMU_LENTELE);
        uzpildytiKlausimuLentele();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KlausimuLentele.LENTELES_VARDAS);
        onCreate(db);
    }

    private void uzpildytiKlausimuLentele(){
        Klausimas k1 = new Klausimas("Kur gyvena pingvinai?" , "Pietų pusrutulyje", "Šiaurės pusrutulyje" , "Abiejuose pusrutuliuose", 1);
        pridetiKlausima(k1);
        Klausimas k2 = new Klausimas("Kokie pingvinai yra patys didžiausi?" , "Imperatoriškieji", "Nykštukiniai" , "Pasakų", 1);
        pridetiKlausima(k2);
        Klausimas k3 = new Klausimas("Kiek skrandžių turi kašalotas?" , "Vieną", "Du" , "Keturis", 3);
        pridetiKlausima(k3);
        Klausimas k4 = new Klausimas("Kiek vidutiniškai gyvena kašalotai?", "10 metų", "60 metų", "100 metų", 2);
        pridetiKlausima(k4);
        Klausimas k5 = new Klausimas("Surask moliusko Kūgenio demonstraciją. Ką naudoja moliuskas medžiodamas grobį?" , "Gėluonį", "Stiprius čiuptuvus" , "Jis - jūrų vegetaras ir nieko nemedžioja", 1);
        pridetiKlausima(k5);
        Klausimas k6 = new Klausimas("Kurie iš nurodytų moliuskų yra Lietuvoje negyvenantys?" , "Pilvakojai", "Galvakojai" , "Dvigeldžiai", 2);
        pridetiKlausima(k6);
        Klausimas k7 = new Klausimas("Kokios iškylios Lietuvos asmenybės vardu pavadinta kriauklė:" , "Grybauskaitės arfa", "Adamkaus marginelė" , "Erlicko taurė", 2);
        pridetiKlausima(k7);
        Klausimas k8 = new Klausimas("Kas koralams suteikia spalvas?" , "Vandens druskingumas", "Vandens užterštumas" , "Dumbliai", 3);
        pridetiKlausima(k8);
        Klausimas k9 = new Klausimas("Koks yra didžiausias sausumos vėžiagyvis?" , "Krabas maldininkas", "Krabas palmių vagis" , "Dumblinis krabas", 2);
        pridetiKlausima(k9);
        Klausimas k10 = new Klausimas("Kuri iš šių žuvų gyvena Lietuvos upėse?" , "Strimelė", "Šamas" , "Geltonoji chirurgžuvė", 2);
        pridetiKlausima(k10);
        Klausimas k11 = new Klausimas("Kuri iš šių žuvų priskiriama pavojingoms vandenyno žuvims?" , "Zebrinė sparnapelekė", "Didžiaakis jūrų kareivis" , "Žiobris", 1);
        pridetiKlausima(k11);
        Klausimas k12 = new Klausimas("Baltijos jūra susiformavo prieš:" , "150mln. metų", "20mln. metų" , "1300 metų", 3);
        pridetiKlausima(k12);
        Klausimas k13 = new Klausimas("Gyvūnai, kurie yra išlikę nuo ledynmečio yra vadinami reliktiniais. Kurie iš pateiktų Baltijos jūros gyventojų yra reliktiniai? " , "Žuvys", "Varliagyviai" , "Moliuskai", 1);
        pridetiKlausima(k13);
        Klausimas k14 = new Klausimas("Su kuo galima sulyginti vidutinį Baltijos jūros gylį?" , "Šatrijos kalno aukščiu", "35 matematikos vadovėlių, sudėtų vienas šalia kito, sudaromu ilgiu" , "22 aukštų namu", 3);
        pridetiKlausima(k14);

    }
    private void pridetiKlausima(Klausimas klausimas){
        ContentValues cv = new ContentValues();
        cv.put(KlausimuLentele.STULPELIO_KLAUSIMAS, klausimas.getKlausimas());
        cv.put(KlausimuLentele.STULPELIO_VARIANTAS1, klausimas.getVariantas1());
        cv.put(KlausimuLentele.STULPELIO_VARIANTAS2, klausimas.getVariantas2());
        cv.put(KlausimuLentele.STULPELIO_VARIANTAS3, klausimas.getVariantas3());
        cv.put(KlausimuLentele.STULPELIO_ATSAKYMO_NR, klausimas.getAtsakymoNr());

        db.insert(KlausimuLentele.LENTELES_VARDAS, null, cv);
    }

    public ArrayList<Klausimas> gautiVisusKlausimus(){
        ArrayList<Klausimas> klausimuSarasas = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + KlausimuLentele.LENTELES_VARDAS, null);
        if(c.moveToFirst()){
            do{
                Klausimas klausimas = new Klausimas();
                klausimas.setKlausimas(c.getString(c.getColumnIndex(KlausimuLentele.STULPELIO_KLAUSIMAS)));
                klausimas.setVariantas1(c.getString(c.getColumnIndex(KlausimuLentele.STULPELIO_VARIANTAS1)));
                klausimas.setVariantas2(c.getString(c.getColumnIndex(KlausimuLentele.STULPELIO_VARIANTAS2)));
                klausimas.setVariantas3(c.getString(c.getColumnIndex(KlausimuLentele.STULPELIO_VARIANTAS3)));
                klausimas.setAtsakymoNr(c.getInt(c.getColumnIndex(KlausimuLentele.STULPELIO_ATSAKYMO_NR)));
                klausimuSarasas.add(klausimas);
            }while (c.moveToNext());
        }
        c.close();
        return klausimuSarasas;
    }
}
