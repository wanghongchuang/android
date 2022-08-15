package com.example.android.ui.activity

import androidx.lifecycle.Observer
import com.example.android.R
import com.example.android.base.BaseViewModelActivity
import com.example.android.common.bus.Bus
import com.example.android.common.bus.REFRESH_LOGIN_SUCCESS
import com.example.android.ext.hideSoftInput
import com.example.android.tools.makeStatusBarTransparent
import com.example.android.tools.showError
import com.example.android.tools.start
import com.example.android.ui.dialog.LoadingDialog
import com.example.android.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseViewModelActivity<LoginViewModel>() {

    override fun getViewModelClass() = LoginViewModel::class.java
    private var loadingDialog: LoadingDialog? = null

    override fun initData() {
        initClick()
    }

    private fun initClick() {
        stvRegister.setOnClickListener {
            start(this, RegisterActivity::class.java)
        }

        stvLogin.setOnClickListener {
            it.hideSoftInput()
            login()
        }
    }

    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        if (username.isEmpty() || password.isEmpty()) {
            showError(R.string.lose_username_password)
            return
        }
        if (loadingDialog == null) loadingDialog = LoadingDialog(this)
        loadingDialog?.setDesc(R.string.logging)
        mViewModel.login(username, password)
    }

    override fun initView() {
        makeStatusBarTransparent(this)
    }

    override fun getLayoutRes() = R.layout.activity_login

    override fun observe() {
        mViewModel.apply {
            userBeanLiveData.observe(this@LoginActivity, Observer {
                // 登录成功
                Bus.post(REFRESH_LOGIN_SUCCESS, it)
                finish()
            })

            loginStatusLiveData.observe(this@LoginActivity, Observer {
                if (it) loadingDialog?.show() else loadingDialog?.dismiss()
            })
        }
    }
}