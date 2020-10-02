package com.bryanortiz.bluesoft.bluebank.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String ACCOUNT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Cuentas` (`idCuenta` TEXT," +
            " `Nombre_Cuenta` TEXT," +
            " `Numero_Cuenta` TEXT," +
            " `Saldo` TEXT)";

    private static final String DB_NAME = "BLUEBANK";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea DB
        db.execSQL(ACCOUNT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
