package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseViewModel
import com.example.android.http.bean.UserBean
import com.example.android.repository.RegisterRepository

class RegisterViewModel : BaseViewModel() {
    private val registerRepository by lazy { RegisterRepository() }
    val userBeanLiveData = MutableLiveData<UserBean>()
    val registerStatusLiveData = MutableLiveData<Boolean>()

    fun register(username: String, password: String, rePassword: String) {
        launch(block = {
            registerStatusLiveData.value = true
            userBeanLiveData.value = registerRepository.register(username, password, rePassword)
            registerStatusLiveData.value = false
        }, error = { registerStatusLiveData.value = false })
    }
}