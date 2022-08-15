package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseViewModel
import com.example.android.http.bean.UserBean
import com.example.android.repository.LoginRepository

class LoginViewModel : BaseViewModel() {
    private val loginRepository by lazy { LoginRepository() }
    val userBeanLiveData = MutableLiveData<UserBean>()
    val loginStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun login(username: String, password: String) {
        launch(block = {
            loginStatusLiveData.value = true
            val userBean = loginRepository.login(username, password)
            userBeanLiveData.value = userBean
            loginStatusLiveData.value = false
        }, error = { loginStatusLiveData.value = false })
    }
}