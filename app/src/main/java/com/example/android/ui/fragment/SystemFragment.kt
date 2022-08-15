package com.example.android.ui.fragment

import android.graphics.Color
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.android.R
import com.example.android.base.BaseRecyclerViewModelFragment
import com.example.android.http.bean.SystemCategoryBean
import com.example.android.listener.OnTagClickListener
import com.example.android.tools.imgTint
import com.example.android.tools.showError
import com.example.android.tools.start
import com.example.android.ui.adapter.SystemCategoryAdapter
import com.example.android.ui.dialog.LocateTagViewDialog
import com.example.android.ui.widget.SmoothTopScroller
import com.example.android.viewmodel.SystemCategoryViewModel
import kotlinx.android.synthetic.main.layout_swipe_recycler.*
import kotlinx.android.synthetic.main.toolbar_fragment.*

/**
 * 体系
 */
class SystemFragment :
    BaseRecyclerViewModelFragment<SystemCategoryBean, SystemCategoryViewModel>(),
    OnTagClickListener {
    private lateinit var adapter: SystemCategoryAdapter
    override fun getLayoutRes() = R.layout.fragment_system

    override fun lazyInitData() {
        mViewModel.getSystemCategory()
    }

    override fun getAdapter(): BaseQuickAdapter<SystemCategoryBean, BaseViewHolder> = adapter

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout = swipeRefreshLayout

    override fun initView() {
        super.initView()
        setCenterText(R.string.home_system)
        setRightIcon(R.mipmap.icon_system_locate)
        imgTint(ivRight,Color.BLACK)
    }

    override fun initRecyclerView() {
        val manager = LinearLayoutManager(mContext)
        recyclerView.layoutManager = manager
        adapter = SystemCategoryAdapter(listener = this)
        recyclerView.adapter = adapter
    }

    override fun initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener { mViewModel.getSystemCategory() }
    }

    override fun getViewModelClass() = SystemCategoryViewModel::class.java

    override fun observe() {
        super.observe()
        mViewModel.apply {
            categories.observe(viewLifecycleOwner, Observer {
                adapter.setList(it)
                ivRight.isVisible = !categories.value.isNullOrEmpty()
            })
        }
    }

    override fun onTagClick(itemPosition: Int, tagPosition: Int) {

    }

    override fun onRightIconClick() {
        super.onRightIconClick()
        val categories: List<SystemCategoryBean>? = mViewModel.categories.value
        if (categories.isNullOrEmpty()) {
            // 没有数据
            showError(R.string.no_valid_data)
        } else {
            val titles = getTitles(categories)
            LocateTagViewDialog(
                mContext,
                titles,
                object : OnTagClickListener {
                    override fun onTagClick(itemPosition: Int, tagPosition: Int) {
                        val scroller = SmoothTopScroller(mContext)
                        scroller.targetPosition = itemPosition
                        recyclerView.layoutManager?.startSmoothScroll(scroller)
                    }
                }).show()
        }
    }

    private fun getTitles(categories: List<SystemCategoryBean>?): List<String> {
        if (categories.isNullOrEmpty()) return emptyList()
        val titles = mutableListOf<String>()
        categories.forEach {
            titles.add(it.name)
        }
        return titles
    }
}
