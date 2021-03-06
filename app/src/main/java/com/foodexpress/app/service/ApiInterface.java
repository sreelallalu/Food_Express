package com.foodexpress.app.service;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sreelal on 6/12/17.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> login
            (@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("hotelregister.php")
    Call<ResponseBody> hotelreg
            (@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("userreg.php")
    Call<ResponseBody> userreg
            (@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("hotel_order.php")
    Call<ResponseBody> odering
            (@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("oder_list.php")
    Call<ResponseBody> order_list
            (@FieldMap HashMap<String, String> hashMap);

    @GET("hotellist.php")
    Call<ResponseBody> hotellist
            ();

    @FormUrlEncoded
    @POST ("cancel_list.php")
    Call<ResponseBody> cancelist
            (@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST ("cancel_order.php")
    Call<ResponseBody> canceorder
            (@FieldMap HashMap<String, String> hashMap);

  /*  @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> register(@FieldMap HashMap<String, String> hashMap);


    @FormUrlEncoded
    @POST("profile_updation.php")
    Call<ResponseBody> profileupdation(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("semlist.php")
    Call<SemModel> semesterlist(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("subjectlist.php")
    Call<SubjectModel> subjectlist(@FieldMap HashMap<String, String> hashMap);
*/



   /* @GET("branchlist.php")
    Call<BatchModel> brachlist();


    @FormUrlEncoded
    @POST("attendance.php")
    Call<AddSuccess> attendance(@FieldMap HashMap<String, String> hashMap);


    @FormUrlEncoded
    @POST("addnotes.php")
    Call<AddSuccess> addnotes(@FieldMap HashMap<String, String> hashMap);
    @FormUrlEncoded
    @POST("assignment.php")
    Call<AddSuccess> assignment(@FieldMap HashMap<String, String> hashMap);

    @GET("category_list.php")
    Call<ResponseBody> catgerylist();

    @FormUrlEncoded
    @POST("pass_change.php")
    Call<ResponseBody> changepass(@Field("user_id") String id,
                                  @Field("user_password") String newpass
    );
    @FormUrlEncoded
    @POST("studentlist.php")
    Call<StudentModel> studentlist(@FieldMap HashMap<String, String> hashMap
    );

    @FormUrlEncoded
    @POST("forgotpass.php")
    Call<ResponseBody> forgotpass(@Field("user_email") String email
    );

    @FormUrlEncoded
    @POST("request.php")
    Call<ResponseBody> requestsend(@FieldMap HashMap<String, String> hashMap);


    @FormUrlEncoded
    @POST("appointment_result.php")
    Call<ResponseBody> request_send(@Field("result_id") String id);*/


}
