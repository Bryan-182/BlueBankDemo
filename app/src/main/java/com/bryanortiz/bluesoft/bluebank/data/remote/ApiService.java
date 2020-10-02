package com.bryanortiz.bluesoft.bluebank.data.remote;

import com.bryanortiz.bluesoft.bluebank.data.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("accounts")
    Call<List<Account>> getAccounts();

    @PUT("accounts/{id}")
    Call<Account> updateAccount(@Path("id") String id, @Body Account account);

    @POST("accounts")
    Call<Account> createAccount(@Body Account account);
}
