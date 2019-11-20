package com.catatanasad.crudmakanan.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.60.171/server_resto_ios/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ApiInteface service = retrofit.create(ApiInteface.class);

}
