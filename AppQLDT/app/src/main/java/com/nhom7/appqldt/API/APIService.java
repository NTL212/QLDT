package com.nhom7.appqldt.API;

import com.nhom7.appqldt.Activitys.DTO.FileDTO;
import com.nhom7.appqldt.Activitys.DTO.ProjectDTO;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.DeTaiCanPheDuyet;
import com.nhom7.appqldt.Models.Lecturer;
import com.nhom7.appqldt.Models.MessageResponse;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.ProjectChiTietQL;
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


    // Lớp phản hồi để ánh xạ dữ liệu JSON
    @POST("api/login")
    @FormUrlEncoded
    Call<APIResponse<Account>> login(@Field("username") String username, @Field("password") String password);
    // Lớp phản hồi để ánh xạ dữ liệu JSON


    @GET("api/project")
    Call<APIResponse<List<Project>>> getAllProject();

    @GET("lecturer-project/api/list")
    Call<APIResponse<List<Project>>> getAllProjectActiveProjectForLecturer();

    @GET("lecturer-project/api/detail")
    Call<APIResponse<Project>> getProjectDetailForLecturer(@Query("id") String id);

    @GET("lecturer-project/api/get-lecture")
    Call<APIResponse<Lecturer>> getLecturerByID(@Query("id") String id);

    @GET("lecturer-project/api/myproj")
    Call<APIResponse<List<ProjectDTO>>> getAllMyProjectForLecturer(@Query("id") String id);

    @POST("lecturer-project/api/propose")
    Call<APIResponse<Project>> proposeProjectForLecturer(@Body Project project);

    @POST("lecturer-project/api/register-project")
    @FormUrlEncoded
    Call<APIResponse<Registration>> regisProjectLecture(@Field("lectCode") String lectCode, @Field("projCode") String projectCode);

    @POST("lecturer-project/api/submit")
    @Multipart
    Call<APIResponse<FileDTO>> submitFileLecture(
            @Part MultipartBody.Part file,
            @Query("projCode") String projCode,
            @Query("lectCode") String lectCode
    );

    @GET("lecturer-notification/api/getSendMessage")
    Call<APIResponse<List<Notification>>> getAllSendedMessageLecture(@Query("id") String id);

    @GET("lecturer-notification/api/getNotify")
    Call<APIResponse<List<Notification>>> getAllReceiveMessageLecture(@Query("id") String id);

    @POST("lecturer-notification/api/sendMessage")
    @FormUrlEncoded
    Call<APIResponse<Notification>> sendMessageLecture(@Field("lectCode") String id, @Field("recieveperson") String receiveId, @Field("messagetitle") String title, @Field("messagecontent") String content);

    @GET("project/api/showdetailform")
    Call<APIResponse<Project>> getDetailProject(@Query("id") String id);

    @GET("lecturer-notification/api/getAllAcc")
    Call<APIResponse<List<Account>>> getAllAcc();

    @GET("api/topic")
    Call<APIResponse<List<Topic>>> getAllTopic();

    //    @POST("topic")
//    @FormUrlEncoded
//    Call<APIResponse<Topic>> insertTopic(@Field("topicCode") String topicCode,
//                                         @Field("name") String name,
//                                         @Field("isEnabled") boolean isEnabled);
////    gui dang json
    @POST("api/topic")
    Call<APIResponse<Topic>> insertTopic(@Body Topic topic);


    //    {
//        "projectCode": "",
//            "name": "",
//            "createDate": "2024-06-01",
//            "description": "",
//            "maxMember": 0,
//            "openRegDate": "2024-06-01",
//            "closeRegDate": "2024-06-01",
//            "startDate": "2024-06-01",
//            "endDate": "2024-06-01",
//            "acceptanceDate": "2024-06-01",
//            "estBudget": 0.0,
//            "result": "",
//            "comment": "",
//            "topic": {},
//        "lecturer": {},
//        "aCouncil": {},
//        "isProposed": false
//    }
    @GET("project/api/getAll")
    Call<APIResponse<List<Project>>> getAllProjectManager();

    @POST("project/api/createProject")
    Call<APIResponse<Project>> createProject(@Body Project project);


    @FormUrlEncoded
    @POST("project/api/getById/")
    Call<APIResponse<ProjectChiTietQL>> getProjectById(@Field("projectId") String projectId);

    @POST("project/api/updateProject")
    Call<APIResponse<Project>> updateProject(@Body Project project);
    @POST("project/api/getPendingApproval")
    Call<APIResponse<List<DeTaiCanPheDuyet>>> getPendingApproval();
}
