package com.jurumuziejus.app;
//tiltas tarp appso ir database :)
//cia yra klausimas : klausimas atsakymu variantai ir teisingas ats

import android.os.Parcel;
import android.os.Parcelable;

public class Klausimas implements Parcelable {
    private String klausimas;
    private String variantas1;
    private String variantas2;
    private String variantas3;
    private int atsakymoNr;

    public Klausimas(){}

    public Klausimas(String klausimas, String variantas1, String variantas2, String variantas3, int atsakymoNr) {
        this.klausimas = klausimas;
        this.variantas1 = variantas1;
        this.variantas2 = variantas2;
        this.variantas3 = variantas3;
        this.atsakymoNr = atsakymoNr;
    }

    protected Klausimas(Parcel in) {
        klausimas = in.readString();
        variantas1 = in.readString();
        variantas2 = in.readString();
        variantas3 = in.readString();
        atsakymoNr = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(klausimas);
        dest.writeString(variantas1);
        dest.writeString(variantas2);
        dest.writeString(variantas3);
        dest.writeInt(atsakymoNr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Klausimas> CREATOR = new Creator<Klausimas>() {
        @Override
        public Klausimas createFromParcel(Parcel in) {
            return new Klausimas(in);
        }

        @Override
        public Klausimas[] newArray(int size) {
            return new Klausimas[size];
        }
    };

    public String getKlausimas() {
        return klausimas;
    }

    public void setKlausimas(String klausimas) {
        this.klausimas = klausimas;
    }

    public String getVariantas1() {
        return variantas1;
    }

    public void setVariantas1(String variantas1) {
        this.variantas1 = variantas1;
    }

    public String getVariantas2() {
        return variantas2;
    }

    public void setVariantas2(String variantas2) {
        this.variantas2 = variantas2;
    }

    public String getVariantas3() {
        return variantas3;
    }

    public void setVariantas3(String variantas3) {
        this.variantas3 = variantas3;
    }

    public int getAtsakymoNr() {
        return atsakymoNr;
    }

    public void setAtsakymoNr(int atsakymoNr) {
        this.atsakymoNr = atsakymoNr;
    }
}
