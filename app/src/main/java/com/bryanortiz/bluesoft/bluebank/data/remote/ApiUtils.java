package com.bryanortiz.bluesoft.bluebank.data.remote;

public class ApiUtils {

    private ApiUtils() {
    }

    //Ip con la que se inicie el json-server
    static final String BASE_URL = "http://192.168.0.27:3000/";

    public static ApiService getAPIService() {
        return Client.getClient().create(ApiService.class);
    }

}
