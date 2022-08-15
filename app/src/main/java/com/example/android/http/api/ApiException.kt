package com.example.android.http.api

import java.lang.RuntimeException

class ApiException(val errorCode: Int, val errorMsg: String) : RuntimeException()