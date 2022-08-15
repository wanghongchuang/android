package com.example.android.repository

import com.example.android.http.api.ApiEngine
import com.example.android.http.bean.UserBean

class RegisterRepository {
    suspend fun register(username: String, password: String, rePassword: String): UserBean {
        return ApiEngine.getApiService().register(username, password, rePassword).getApiData()
    }
}