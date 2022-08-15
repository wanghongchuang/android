package com.example.android.repository

import com.example.android.http.api.ApiEngine
import com.example.android.http.bean.UserBean

class LoginRepository {
    suspend fun login(username: String, password: String): UserBean {
        return ApiEngine.getApiService().login(username, password).getApiData()
    }
}