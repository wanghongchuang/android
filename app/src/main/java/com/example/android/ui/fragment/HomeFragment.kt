package com.example.android.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.ext.addOnPageChangedListener
import com.example.android.ext.addOnTabChangeListener
import com.example.android.tools.start
import com.example.android.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_tab.*

class HomeFragment : BaseFragment() {
    private lateinit var fragments: List<Fragment>

    override fun getLayoutRes() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFragments()
        setViewPagerTabLayout()
    }

    private fun setViewPagerTabLayout() {
        val titles = listOf(
            resources.getString(R.string.home_main),
            resources.getString(R.string.home_newest_share)
        )
        val adapter = ViewPagerAdapter(childFragmentManager, fragments, titles)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = fragments.size
        titles.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
        viewPager.addOnPageChangedListener {
            tabLayout.getTabAt(it)?.select()
        }

        tabLayout.addOnTabChangeListener(selected = {
            it?.let { viewPager.setCurrentItem(it.position, true) }
        }, reselected = {})
    }

    private fun loadFragments() {
        fragments = listOf(
//            HomeFragment(),
//            HomeFragment()
        )
    }

    override fun lazyInitData() {
    }

    override fun initView() {
//        ivSearch.setOnClickListener {
//            start(mContext, SearchActivity::class.java)
//        }
    }
}
