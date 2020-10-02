package com.bryanortiz.bluesoft.bluebank.repository;

import android.content.Context;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;

import java.util.List;

public interface Repository {

    List<Account> checkAccounts(Context context);

    void updateDB(List<Account> accountList, Context context);

    void updateAccount(Account account);

    void createAccount(Account account);
}
