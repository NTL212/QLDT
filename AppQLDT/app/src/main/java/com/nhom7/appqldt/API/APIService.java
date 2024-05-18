package com.nhom7.appqldt.API;

import com.nhom7.appqldt.Activitys.DTO.AdminDTO;
import com.nhom7.appqldt.Activitys.DTO.FileDTO;
import com.nhom7.appqldt.Activitys.DTO.ProjectDTO;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.Admin;
import com.nhom7.appqldt.Models.Class;
import com.nhom7.appqldt.Models.Faculty;
import com.nhom7.appqldt.Models.Lecturer;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.Models.Major;
import com.nhom7.appqldt.Models.ManagerAccount;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Registration;
import com.nhom7.appqldt.Models.Student;
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
    @POST("api/login")
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

    @GET("admincontroller/api/listlecturer")
    Call<APIResponse<List<LecturerAccount>>> getAllLecturer();

    @GET("admincontroller/api/listmanager")
    Call<APIResponse<List<ManagerAccount>>> getAllManager();

    @GET("admincontroller/api/getlecturerbyid")
    Call<APIResponse<LecturerAccount>> getLecturerById(@Query("magv") String id);

    @GET("admincontroller/api/getmanagerbyid")
    Call<APIResponse<ManagerAccount>> getManagerById(@Query("manv") String id);

    @POST("admincontroller/api/insertlecturer")
    @FormUrlEncoded
    Call<APIResponse<String>> insertLecturer(
            @Field("magv") String lecturerCode,
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name,
            @Field("birthday") String birthday,
            @Field("address") String address,
            @Field("idNum") String idNum,
            @Field("phoneNum") String phoneNum,
            @Field("email") String email,
            @Field("sex") String sex,
            @Field("falculityCode") String falculityCode
    );
    @POST("admincontroller/api/deleteacc")
    @FormUrlEncoded
    Call<APIResponse<String>> deleteAccount(
            @Field("accCode") String accCode
    );

    @POST("admincontroller/api/editacc")
    @FormUrlEncoded
    Call<APIResponse<String>> editAccount(
            @Field("adCode") String username,
            @Field("password") String password,
            @Field("name") String name,
            @Field("birthday") String birthday,
            @Field("address") String address,
            @Field("idNum") String idNum,
            @Field("phoneNum") String phoneNum,
            @Field("email") String email,
            @Field("sex") String sex,
            @Field("falculityCode") String falculityCode
    );

    @GET("admincontroller/api/allfalculities")
    Call<APIResponse<List<Faculty>>> getAllFalculities();

    @POST("admincontroller/api/insertmanager")
    @FormUrlEncoded
    Call<APIResponse<String>> insertManager(
            @Field("empCode") String empCode,
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name,
            @Field("birthday") String birthday,
            @Field("address") String address,
            @Field("idNum") String idNum,
            @Field("phoneNum") String phoneNum,
            @Field("email") String email,
            @Field("sex") String sex
    );

    @GET("admincontroller/api/liststudent")
    Call<APIResponse<List<StudentDTO>>> getAllStudent();

    @POST("admincontroller/api/getstudentbyid")
    @FormUrlEncoded
    Call<APIResponse<Student>> getStudentByid(
            @Field("studentId") String studentId);

    @GET("admincontroller/api/getmajorbyfalculityid")
    Call<APIResponse<List<Major>>> getMajorByFalculityCode(
            @Query("khoa") String khoa
    );

    @GET("admincontroller/api/getclassbymajorcode")
    Call<APIResponse<List<Class>>> getClassByMajorCode(
            @Query("manganh") String manganh
    );

    @POST("admincontroller/api/insertstudent")
    @FormUrlEncoded
    Call<APIResponse<String>> insertStudent(
            @Field("studentCode") String studentCode,
            @Field("name") String name,
            @Field("birthday") String birthday,
            @Field("address") String address,
            @Field("idNum") String idNum,
            @Field("phoneNum") String phoneNum,
            @Field("email") String email,
            @Field("sex") String sex,
            @Field("classCode") String classCode
    );
    @POST("admincontroller/api/deletestudent")
    @FormUrlEncoded
    Call<APIResponse<String>> deleteStudent(
            @Field("studentCode") String studentCode
    );

    @POST("admincontroller/api/updatestudent")
    @FormUrlEncoded
    Call<APIResponse<String>> updateStudent(
            @Field("studentCode") String studentCode,
            @Field("name") String name,
            @Field("birthday") String birthday,
            @Field("address") String address,
            @Field("idNum") String idNum,
            @Field("phoneNum") String phoneNum,
            @Field("email") String email,
            @Field("sex") String sex,
            @Field("classCode") String classCode
    );
    @GET("admincontroller/api/getadminbyid")
    Call<APIResponse<AdminDTO>> getAdminByAdCode(
            @Query("adCode") String adCode
    );

}
