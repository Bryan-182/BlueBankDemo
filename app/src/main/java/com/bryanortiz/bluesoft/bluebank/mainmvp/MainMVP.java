package com.bryanortiz.bluesoft.bluebank.mainmvp;

import android.content.Context;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;

import java.util.List;

import dagger.Module;

@Module
public interface MainMVP {

    interface View {

        void showResult(Account account, int from);
    }

    interface Presenter {

        void setView(MainMVP.View view);

        void createDB(Context context);

        List<Account> getAccounts(Context context);

        void updateDB(List<Account> accountList, Context context);

        void createAccount(String accountName, String initValue, Context context);

        void updateAccount(Account accountNo, Context context);

        void transferMoney(String accountNo, String value, Context context);

        void retirementMoney(String accountNo, String value, Context context);

        void queryBalance(String account, Context context);

        void showResult(Account account, Context context, int from);
    }

    interface Model {

        void createDb(Context context);

        List<Account> checkAccounts(Context context, int from);

        void updateDB(List<Account> accountList, Context context);

        void updateAccount(Account accountNo, Context context, MainMVP.Presenter presenter);

        void createAccount(String accountName, String initValue, Context context, MainMVP.Presenter presenter);

        void transferMoney(String accountNo, String value, Context context, MainMVP.Presenter presenter);

        void retirementMoney(String accountNo, String value, Context context, MainMVP.Presenter presenter);

        void queryBalance(String accountNo, Context context, MainMVP.Presenter presenter);
    }
}
