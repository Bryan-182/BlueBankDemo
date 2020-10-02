package com.bryanortiz.bluesoft.bluebank.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("balance")
    @Expose
    private String balance;

    /**
     * No args constructor for use in serialization
     */
    public Account() {
    }

    /**
     * @param balance
     * @param name
     * @param id
     * @param account
     */
    public Account(Integer id, String name, String account, String balance) {
        super();
        this.id = id;
        this.name = name;
        this.account = account;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
