package com.catatanasad.crudmakanan.network;

import com.catatanasad.crudmakanan.model.ResponseMakanan;
import com.catatanasad.crudmakanan.model_login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInteface {

    @GET("Api/getMakanan")
    Call<ResponseMakanan> getMakanan();

    @FormUrlEncoded
    @POST("Api/insertMakanan")
    Call<ResponseMakanan> insertMakanan(@Field("name") String nama,
                                        @Field("price") String harga,
                                        @Field("gambar") String urlGambar);

    @FormUrlEncoded
    @POST("Api/updateMakanan")
    Call<ResponseMakanan> updateMakanan(@Field("name") String nama,
                                        @Field("price") String harga,
                                        @Field("gambar") String urlGambar,
                                        @Field("id") String id);

    @FormUrlEncoded
    @POST("Api/deleteMakanan")
    Call<ResponseMakanan> deleteMakanan(@Field("id") String id);

    @FormUrlEncoded
    @POST("Api/login")
    Call<ResponseLogin> login(@Field("email") String email,
                              @Field("password") String password);
}