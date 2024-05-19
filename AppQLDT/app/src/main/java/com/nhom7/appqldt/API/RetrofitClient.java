package com.nhom7.appqldt.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static Retrofit retrofit2;
    private static final String BASE_URL = "http://26.172.196.40:8080/QuanLyDeTaiKhoaHoc_tmp/";
//    private static final String BASE_URL = "http://10.0.2.2:8080/QuanLyDeTaiKhoaHoc_tmp/";
    // Tạo đối tượng Gson tùy chỉnh với setLenient(true)

    private static final String BASE_URL_PROJ = "http://26.172.196.40:8080/QuanLyDeTaiKhoaHoc_tmp/";
    // Tạo đối tượng Gson tùy chỉnh với setLenient(true)

    public static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getRetrofitInstance2() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_PROJ)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit2;
    }
}