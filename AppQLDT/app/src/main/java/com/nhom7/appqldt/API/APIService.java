package com.nhom7.appqldt.API;

import com.nhom7.appqldt.Models.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("login")
    Call<MessageResponse> getHelloWorld();
    // Lớp phản hồi để ánh xạ dữ liệu JSON
    @POST("login")
    @FormUrlEncoded
    Call<MessageResponse> postHelloWorld(@Field("messId") String messId, @Field("messContent") String messContent);
    // Lớp phản hồi để ánh xạ dữ liệu JSON
}
