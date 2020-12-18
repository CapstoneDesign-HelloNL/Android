package com.example.areact.server;

import com.example.areact.group.detail.server.GroupHamPersonGet;
import com.example.areact.group.detail.server.GroupNoticeDel;
import com.example.areact.group.detail.server.GroupNoticeGet;
import com.example.areact.group.detail.server.GroupNoticeOneGet;
import com.example.areact.group.detail.server.GroupNoticePost;
import com.example.areact.group.detail.server.GroupScheduleDel;
import com.example.areact.group.detail.server.GroupScheduleGet;
import com.example.areact.group.detail.server.GroupSchedulePost;
import com.example.areact.group.server.GroupAdd;
import com.example.areact.group.server.GroupDelete;
import com.example.areact.group.server.GroupListGet;
import com.example.areact.group.server.GroupSearchAllGet;
import com.example.areact.group.server.GroupSearchOneGet;
import com.example.areact.login.server.DropOutAccountDel;
import com.example.areact.login.server.LogoutAccountPost;
import com.example.areact.login.server.SignInPost;
import com.example.areact.login.server.SignUpPost;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    //about account
    @FormUrlEncoded
    @POST("/api/auth/user")
    Call<SignUpPost> postSignUp(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/api/auth/user/signin")
    Call<SignInPost> postSignIn(@FieldMap HashMap<String, Object> param);

    @POST("/api/auth/user/logout")
    Call<LogoutAccountPost> postLogout();

    @DELETE("/api/auth/user")
    Call<DropOutAccountDel> delDroupout();

    //내가 가입한 그룹 보여주기
    @GET("/api/group/signup")
    Call<GroupListGet> getGroupData();

    //그룹 추가하기
    @FormUrlEncoded
    @POST("/api/group")
    Call<GroupAdd> postGroupAdd(@FieldMap HashMap<String, Object> param);

    //그룹 삭제하기
    @DELETE("/api/group/{groupName}")
    Call<GroupDelete> deleteGroupDel(@Path("groupName") String groupName);

    //그룹 검색하기
    @GET("/api/group/one/{groupName}")
    Call<GroupSearchOneGet> getGroupSearchOne(@Path("groupName") String groupName);

    @GET("/api/group/all/{groupName}")
    Call<GroupSearchAllGet> getGroupSearchAll(@Path("groupName") String groupName);

    //group_detail
    //group_schedule
    @FormUrlEncoded
    @POST("/api/group/{groupName}/schedule")
    Call<GroupSchedulePost> postScheduleData(@Path("groupName") String groupName, @FieldMap HashMap<String, Object> param);

    @GET("/api/group/{groupName}/schedule")
    Call<GroupScheduleGet> getScheduleData(@Path("groupName") String groupName);

    @DELETE("/api/group/{groupName}/schedule")
    Call<GroupScheduleDel> delScheduleData(@Path("groupName") String groupName);

    //group_notice
    @FormUrlEncoded
    @POST("/api/group/{groupName}/notice")
    Call<GroupNoticePost> postNoticeData(@Path("groupName") String groupName, @FieldMap HashMap<String, Object> param);

    @GET("/api/group/{groupName}/notice")
    Call<GroupNoticeGet> getNoticeData(@Path("groupName") String groupName);

    @GET("/api/group/{groupName}/notice/{id}")
    Call<GroupNoticeOneGet> getNoticeDataOne(@Path("groupName") String groupName, @Path("id") Integer id);

    @DELETE("api/group/{groupName}/notice/{id}")
    Call<GroupNoticeDel> delNoticeData(@Path("groupName") String groupName, @Path("id") Integer id);
    //group_gallery

    //그룹 hambar
    //그룹 명단
    @GET("api/group/{groupName}/member")
    Call<GroupHamPersonGet> getGroupHamPerson(@Path("groupName") String groupName);
}