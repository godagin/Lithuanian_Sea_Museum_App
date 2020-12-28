package com.jurumuziejus.app;

import android.provider.BaseColumns;

public final class KlausimynoKontraktas{

    private KlausimynoKontraktas() {}

    public static class KlausimuLentele implements BaseColumns {
        public static final String LENTELES_VARDAS = "klausimyno_klausimai";
        public static final String STULPELIO_KLAUSIMAS = "klausimas";
        public static final String STULPELIO_VARIANTAS1 = "variantas1";
        public static final String STULPELIO_VARIANTAS2 = "variantas2";
        public static final String STULPELIO_VARIANTAS3 = "variantas3";
        public static final String STULPELIO_ATSAKYMO_NR = "atsakymo_nr";

    }
}
