package com.bryanortiz.bluesoft.bluebank.repository;

import android.content.Context;
import android.util.Log;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;
import com.bryanortiz.bluesoft.bluebank.data.remote.ApiService;
import com.bryanortiz.bluesoft.bluebank.data.remote.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository implements Repository {

    private ApiService mApiService = ApiUtils.getAPIService();

    //List final for uptade DB
    List<Account> accountListFinal;

    @Override
    public List<Account> checkAccounts(Context context) {
        mApiService.getAccounts().enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.isSuccessful()) {
                    List<Account> accountList = response.body();
                    accountListFinal = accountList;
                    if (accountList != null) {
                        for (Account account : accountList) {
                            try {
                                Log.d("Debug account id ", account.getId().toString());
                                Log.d("Debug account name ", account.getName());
                                Log.d("Debug account number ", account.getAccount());
                                Log.d("Debug account balance ", account.getBalance());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "accountList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.d("onFailure checkAccounts", t.toString());
            }
        });

        return accountListFinal;
    }

    @Override
    public void updateDB(List<Account> accountList, Context context) {
        //TODO POST Account
    }

    @Override
    public void updateAccount(Account account) {
        mApiService.updateAccount(account.getId().toString(), account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Log.d("PUT", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d("PUT", "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void createAccount(Account account) {
        mApiService.createAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Log.d("POST", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d("POST", "Unable to submit post to API.");
            }
        });
    }
}
