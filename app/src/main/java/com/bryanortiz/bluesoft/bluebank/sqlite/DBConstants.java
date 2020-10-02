package com.bryanortiz.bluesoft.bluebank.sqlite;

public final class DBConstants {

    /**
     * Constants to identify from which repository
     * the information will be extracted
     */
    public static final int API_REPOSITORY = 0;
    public static final int DATABASE_REPOSITORY = 1;

    /**
     * Table Names
     */
    public static final String ACCOUNTS_TABLE_NAME = "Cuentas";

    /**
     * Account table columns
     */
    public static final String ACCOUNT_ID = "idCuenta";
    public static final String ACCOUNT_NAME = "Nombre_Cuenta";
    public static final String ACCOUNT_NUMBER = "Numero_Cuenta";
    public static final String ACCOUNT_BALANCE = "Saldo";

}
