package com.bryanortiz.bluesoft.bluebank.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;
import com.bryanortiz.bluesoft.bluebank.sqlite.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.ACCOUNTS_TABLE_NAME;
import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.ACCOUNT_BALANCE;
import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.ACCOUNT_ID;
import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.ACCOUNT_NAME;
import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.ACCOUNT_NUMBER;

public class DatabaseRepository implements Repository {

    @Override
    public List<Account> checkAccounts(Context context) {
        List<Account> accountList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + ACCOUNTS_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idAccount = cursor.getColumnIndex(ACCOUNT_ID);
                    int nameAccount = cursor.getColumnIndex(ACCOUNT_NAME);
                    int numberAccount = cursor.getColumnIndex(ACCOUNT_NUMBER);
                    int balanceAccount = cursor.getColumnIndex(ACCOUNT_BALANCE);

                    //add row to list
                    accountList.add(new Account(
                            Integer.parseInt(cursor.getString(idAccount)),
                            cursor.getString(nameAccount),
                            cursor.getString(numberAccount),
                            cursor.getString(balanceAccount)
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return accountList;
    }

    @Override
    public void updateDB(List<Account> accountList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (accountList != null) {
            for (Account account : accountList) {
                cv.put(ACCOUNT_ID, account.getId());
                cv.put(ACCOUNT_NAME, account.getName());
                cv.put(ACCOUNT_NUMBER, account.getAccount());
                cv.put(ACCOUNT_BALANCE, account.getBalance());

                if (existId(db, ACCOUNTS_TABLE_NAME, ACCOUNT_ID, account.getId().toString())) {
                    db.update(ACCOUNTS_TABLE_NAME, cv, "idCuenta = " + account.getId(), null);
                } else {
                    db.insert(ACCOUNTS_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void createAccount(Account account) {

    }

    /**
     * Method that traverses the column of the table
     * and searches if the value is already in that table
     *
     * @param db        SQLiteDatabase
     * @param tableName tableName
     * @param columName columnName
     * @param value     value
     * @return Number of records with the same value
     */
    public boolean existId(SQLiteDatabase db, String tableName, String columName, String value) {
        Cursor c;
        boolean exist = false;
        String query = "SELECT count(*) FROM " + tableName + " WHERE " + columName + " = " + value;
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            if (c.getInt(0) > 0) {
                exist = true;
            }
        }
        c.close();
        return exist;
    }
}
