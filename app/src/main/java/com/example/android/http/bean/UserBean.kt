package com.example.android.http.bean

data class UserBean(
    val admin: Boolean,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)
