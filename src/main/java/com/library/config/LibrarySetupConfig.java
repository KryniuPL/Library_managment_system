package com.library.config;

/**
 * Zawiera stałe i zmienne statyczne potrzebne do implementacji logiki
 *
 *Ze względu na możliwość potrzeby zmiany pewnych ustawień gromadzimy je w jednym miejscu
 */

public class LibrarySetupConfig {
    public static double FEE = 0.5;

    public static int BORROW_NOTIFICATION_DAYS = 7;

    public static int BORROW_TIME_DAYS = 90;

    //BORROWSTATES
    public static final int TERM_REACHED = -1;
    public static final int TERM_SOON = 2;


    //STATUS ARRAY INDEXES
    public static final int COMPARISON_RESULT = 0;
    public static final int DAYS_BETWEEN = 1;
    public static final int OBJECT = 2;

}
