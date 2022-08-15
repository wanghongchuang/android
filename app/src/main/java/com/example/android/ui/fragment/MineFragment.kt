package com.example.android.ui.fragment

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.android.R
import com.example.android.base.BaseViewModelFragment
import com.example.android.common.bus.Bus
import com.example.android.common.bus.REFRESH_LOGIN_SUCCESS
import com.example.android.http.bean.UserBean
import com.example.android.sp.UserSpHelper
import com.example.android.tools.showNormal
import com.example.android.tools.start
import com.example.android.ui.activity.AboutAuthorActivity
import com.example.android.ui.activity.LoginActivity
import com.example.android.viewmodel.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.layout_my_info.view.*

class MineFragment : BaseViewModelFragment<MineViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun lazyInitData() {
        myInfoCard.show()
        myInfoCard.civHeadIcon.setOnClickListener {
            if (!UserSpHelper.newHelper().isLogin()) {
                start(mContext, LoginActivity::class.java)
            }
        }

        busObserve()
        initClick()
    }

    override fun initView() {
        setCenterText(R.string.home_mine)
        val userBean = mViewModel.getUserInfo()
        if (userBean == null) {
            infoWhenLogout()
        } else {
            infoWhenLogin(userBean)
        }
    }

    private fun busObserve() {
        Bus.observe<UserBean>(REFRESH_LOGIN_SUCCESS, viewLifecycleOwner, observer = {
            UserSpHelper.newHelper().saveUserInfo(it)
            infoWhenLogin(userBean = it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun infoWhenLogin(userBean: UserBean) {
        myInfoCard.tvNickname.text = userBean.nickname
        myInfoCard.tvId.text = "ID:${userBean.id}"
        myInfoCard.tvTranslucentId.text = userBean.id.toString()
        myInfoCard.civHeadIcon.text = userBean.nickname
        stvLogout.isVisible = true
    }

    private fun infoWhenLogout() {
        myInfoCard.tvNickname.setText(R.string.login_register)
        myInfoCard.tvId.setText(R.string.click_left_login)
        myInfoCard.tvTranslucentId.setText(R.string.ID)
        myInfoCard.civHeadIcon.setText(R.string.app_name)
        stvLogout.isVisible = false
    }

    private fun initClick() {
        stvLookHistory.setOnClickListener {
            showNormal("正在开发中...")
        }
        stvAboutAuthor.setOnClickListener {
            start(mContext, AboutAuthorActivity::class.java)
        }
        stvLogout.setOnClickListener {
            mViewModel.logout()
        }
    }

    override fun observe() {
        mViewModel.logoutLiveData.observe(viewLifecycleOwner, Observer {
            // 退出登录后的操作
            infoWhenLogout()
        })
    }

    override fun getViewModelClass() = MineViewModel::class.java
}
