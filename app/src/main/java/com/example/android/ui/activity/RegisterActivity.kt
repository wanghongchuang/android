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
import com.example.android.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseViewModelActivity<RegisterViewModel>() {
    private var loadingDialog: LoadingDialog? = null
    override fun getViewModelClass() = RegisterViewModel::class.java

    override fun initData() {
        initClick()
    }

    override fun initView() {
        makeStatusBarTransparent(this)
    }

    override fun getLayoutRes() = R.layout.activity_register

    private fun initClick() {
        stvLogin.setOnClickListener {
            start(this, LoginActivity::class.java)
            finish()
        }

        stvRegister.setOnClickListener {
            it.hideSoftInput()
            register()
        }
    }

    override fun observe() {
        mViewModel.apply {
            userBeanLiveData.observe(this@RegisterActivity, Observer {
                Bus.post(REFRESH_LOGIN_SUCCESS, it)
                start(this@RegisterActivity, MainActivity::class.java)
                finish()
            })
            registerStatusLiveData.observe(this@RegisterActivity, Observer {
                if (it) loadingDialog?.show() else loadingDialog?.dismiss()
            })
        }
    }

    private fun register() {
        val username = etUsername.text.toString()
        if (username.isEmpty()) {
            showError(R.string.input_username)
            return
        }
        val password = etPassword.text.toString()
        if (password.isEmpty()) {
            showError(R.string.input_password)
            return
        }
        val rePassword = etRePassword.text.toString()
        if (rePassword.isEmpty()) {
            showError(R.string.password_again)
            return
        }
        if (loadingDialog == null) loadingDialog = LoadingDialog(this)
        mViewModel.register(username, password, rePassword)
    }
}