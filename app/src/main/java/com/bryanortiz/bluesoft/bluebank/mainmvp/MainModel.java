package com.bryanortiz.bluesoft.bluebank.mainmvp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;
import com.bryanortiz.bluesoft.bluebank.repository.ApiRepository;
import com.bryanortiz.bluesoft.bluebank.repository.DatabaseRepository;
import com.bryanortiz.bluesoft.bluebank.sqlite.DbOpenHelper;

import java.util.List;

import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.API_REPOSITORY;
import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.DATABASE_REPOSITORY;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_CREATE_ACCOUNT;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_QUERY;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_RETIREMENT;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_TRANSFER;

public class MainModel implements MainMVP.Model {

    DbOpenHelper dbHelper;
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;

    public MainModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public void createDb(Context context) {
        dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Log.d("Debug", "Database created");
        }
    }

    @Override
    public List<Account> checkAccounts(Context context, int from) {
        List<Account> accountList = null;

        switch (from) {
            case API_REPOSITORY:
                accountList = apiRepository.checkAccounts(context);
                break;
            case DATABASE_REPOSITORY:
                accountList = databaseRepository.checkAccounts(context);
                break;

            default:
                break;
        }
        return accountList;
    }

    @Override
    public void updateDB(List<Account> accountList, Context context) {
        databaseRepository.updateDB(accountList, context);
    }

    @Override
    public void updateAccount(Account accountNo, Context context, MainMVP.Presenter presenter) {
        apiRepository.updateAccount(accountNo);
    }

    @Override
    public void createAccount(String accountName, String initValue, Context context, MainMVP.Presenter presenter) {
        int accountNo = (int) (100000 * Math.random());
        Account account = new Account(null, accountName, String.valueOf(accountNo), initValue);
        apiRepository.createAccount(account);

        presenter.showResult(account, context, TRANS_CREATE_ACCOUNT);
    }

    @Override
    public void transferMoney(String accountNo, String value, Context context, MainMVP.Presenter presenter) {
        List<Account> accountList = apiRepository.checkAccounts(context);
        Account accountFinal = null;
        int valueTransfer = Integer.parseInt(value);

        for (Account account : accountList) {
            if (account.getAccount().equals(accountNo)) {
                int actualValue = Integer.parseInt(account.getBalance());
                String total = String.valueOf(actualValue + valueTransfer);

                accountFinal = new Account(account.getId(), account.getName(), account.getAccount(), total);
                presenter.updateAccount(accountFinal, context);
            }
        }

        presenter.showResult(accountFinal, context, TRANS_TRANSFER);
    }

    @Override
    public void retirementMoney(String accountNo, String value, Context context, MainMVP.Presenter presenter) {
        List<Account> accountList = apiRepository.checkAccounts(context);
        Account accountFinal = null;
        int valueRetirement = Integer.parseInt(value);

        for (Account account : accountList) {
            if (account.getAccount().equals(accountNo)) {
                int actualValue = Integer.parseInt(account.getBalance());

                if (actualValue >= valueRetirement) {
                    String total = String.valueOf(actualValue - valueRetirement);
                    accountFinal = new Account(account.getId(), account.getName(), account.getAccount(), total);
                    presenter.updateAccount(accountFinal, context);
                }
            }
        }

        presenter.showResult(accountFinal, context, TRANS_RETIREMENT);
    }

    @Override
    public void queryBalance(String accountNo, Context context, MainMVP.Presenter presenter) {
        List<Account> accountList = apiRepository.checkAccounts(context);
        Account accountFinal = null;

        for (Account account : accountList) {
            if (account.getAccount().equals(accountNo)) {
                accountFinal = new Account(account.getId(), account.getName(),
                        account.getAccount(), account.getBalance());
            }
        }

        presenter.showResult(accountFinal, context, TRANS_QUERY);
    }
}
