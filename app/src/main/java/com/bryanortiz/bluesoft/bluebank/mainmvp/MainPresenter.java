package com.bryanortiz.bluesoft.bluebank.mainmvp;

import android.content.Context;

import androidx.annotation.Nullable;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;

import java.util.List;

import static com.bryanortiz.bluesoft.bluebank.sqlite.DBConstants.API_REPOSITORY;

public class MainPresenter implements MainMVP.Presenter {

    @Nullable
    private MainMVP.View view;
    private MainMVP.Model model;

    public MainPresenter(MainMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MainMVP.View view) {
        if (view != null) {
            this.view = view;
        }
    }

    @Override
    public void createDB(Context context) {
        model.createDb(context);
    }

    @Override
    public List<Account> getAccounts(Context context) {
        return model.checkAccounts(context, API_REPOSITORY);
    }

    @Override
    public void updateDB(List<Account> accountList, Context context) {
        model.updateDB(accountList, context);
    }

    @Override
    public void createAccount(String accountName, String initValue, Context context) {
        model.createAccount(accountName, initValue, context, this);
    }

    @Override
    public void updateAccount(Account account, Context context) {
        model.updateAccount(account, context, this);
    }

    @Override
    public void transferMoney(String account, String value, Context context) {
        model.transferMoney(account, value, context, this);
    }

    @Override
    public void retirementMoney(String account, String value, Context context) {
        model.retirementMoney(account, value, context, this);
    }

    @Override
    public void queryBalance(String account, Context context) {
        model.queryBalance(account, context, this);
    }

    @Override
    public void showResult(Account account, Context context, int from) {
        if (view != null) {
            view.showResult(account, from);
        }
    }
}
