package com.nhom7.appqldt.API;

import com.nhom7.appqldt.Activitys.DTO.FileDTO;
import com.nhom7.appqldt.Activitys.DTO.ProjectDTO;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.Lecturer;
import com.nhom7.appqldt.Models.MessageResponse;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Registration;
import com.nhom7.appqldt.Models.Topic;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {
    @GET("topic")
    Call<APIResponse> getHelloWorld();

    // Lớp phản hồi để ánh xạ dữ liệu JSON
    @POST("login")
    @FormUrlEncoded
    Call<APIResponse<Account>> login(@Field("username") String username, @Field("password") String password);
    // Lớp phản hồi để ánh xạ dữ liệu JSON


    @GET("project")
    Call<APIResponse<List<Project>>> getAllProject();

    @GET("lecturer-project/list")
    Call<APIResponse<List<Project>>> getAllProjectActiveProjectForLecturer();

    @GET("lecturer-project/detail")
    Call<APIResponse<Project>> getProjectDetailForLecturer(@Query("id") String id);

    @GET("lecturer-project/get-lecture")
    Call<APIResponse<Lecturer>> getLecturerByID(@Query("id") String id);

    @GET("lecturer-project/myproj")
    Call<APIResponse<List<ProjectDTO>>> getAllMyProjectForLecturer(@Query("id") String id);

    @POST("lecturer-project/propose")
    Call<APIResponse<Project>> proposeProjectForLecturer(@Body Project project);

    @POST("lecturer-project/register-project")
    @FormUrlEncoded
    Call<APIResponse<Registration>> regisProjectLecture(@Field("lectCode") String lectCode, @Field("projCode") String projectCode);

    @POST("lecturer-project/submit")
    @Multipart
    Call<APIResponse<FileDTO>> submitFileLecture(
            @Part MultipartBody.Part file,
            @Query("projCode") String projCode,
            @Query("lectCode") String lectCode
    );

    @GET("lecturer-notification/getSendMessage")
    Call<APIResponse<List<Notification>>> getAllSendedMessageLecture(@Query("id") String id);

    @GET("lecturer-notification/getNotify")
    Call<APIResponse<List<Notification>>> getAllReceiveMessageLecture(@Query("id") String id);

    @POST("lecturer-notification/sendMessage")
    @FormUrlEncoded
    Call<APIResponse<Notification>> sendMessageLecture(@Field("lectCode") String id, @Field("recieveperson") String receiveId, @Field("messagetitle") String title, @Field("messagecontent") String content);

    @GET("project/showdetailform")
    Call<APIResponse<Project>> getDetailProject(@Query("id") String id);

    @GET("topic")
    Call<APIResponse<List<Topic>>> getAllTopic();
}
