package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseViewModel
import com.example.android.http.api.ApiEngine
import com.example.android.http.bean.UserBean
import com.example.android.sp.UserSpHelper

class MineViewModel : BaseViewModel() {
    val logoutLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getUserInfo(): UserBean? {
        return UserSpHelper.newHelper().getUserInfo()
    }

    fun logout() {
        UserSpHelper.newHelper().clearUserInfo()
        ApiEngine.cookieJar.clear()
        logoutLiveData.value = true
    }
}