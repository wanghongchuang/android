package com.example.android.http.api

import com.example.android.http.bean.*
import retrofit2.http.*

interface ApiService {
    // 根路径
    companion object {
        const val BASE_URL = ""
    }

    // 登录
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiCommonResponse<UserBean>

    // 注册
    @POST("user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): ApiCommonResponse<UserBean>

    // 热词
    @GET("hotkey/json")
    suspend fun getHotKey(): ApiCommonResponse<List<HotKeyBean>>
}