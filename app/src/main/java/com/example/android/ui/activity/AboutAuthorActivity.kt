package com.example.android.ui.activity

import android.os.Bundle
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.tools.start
import kotlinx.android.synthetic.main.activity_about_author.*

class AboutAuthorActivity : BaseActivity() {

    override fun initData() {
        initClick()
    }

    override fun initView() {
        setCenterText(R.string.about_author)
    }

    override fun getLayoutRes() = R.layout.activity_about_author

    private fun initClick() {
        github.setOnClickListener {
            start(this, AgentWebActivity::class.java, Bundle().apply {
                putString(AgentWebActivity.URL, "https://github.com/SmartCyl")
            })
        }
        csdn.setOnClickListener {
            start(this, AgentWebActivity::class.java, Bundle().apply {
                putString(AgentWebActivity.URL, "https://blog.csdn.net/u014112893")
            })
        }
    }
}