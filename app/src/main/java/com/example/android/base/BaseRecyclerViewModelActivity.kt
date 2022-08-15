package com.example.android.base

import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.loadmore.LoadMoreStatus
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 在使用RecyclerView的Activity界面使用
 */
abstract class BaseRecyclerViewModelActivity<T, VM : BaseRecyclerViewModel> :
    BaseViewModelActivity<VM>() {
    override fun observe() {
        observeStatus()
    }

    private fun observeStatus() {
        mViewModel.loadMoreStatus.observe(this, Observer {
            when (it) {
                LoadMoreStatus.Complete -> getAdapter().loadMoreModule.loadMoreComplete()
                LoadMoreStatus.Fail -> getAdapter().loadMoreModule.loadMoreFail()
                LoadMoreStatus.End -> getAdapter().loadMoreModule.loadMoreEnd()
                else -> return@Observer
            }
        })

        mViewModel.refreshStatus.observe(this, Observer {
            getSwipeRefreshLayout().isRefreshing = it
        })
    }

    override fun initView() {
        initRecyclerView()
        initRefreshLayout()
    }

    abstract fun getAdapter(): BaseQuickAdapter<T, BaseViewHolder>
    abstract fun getSwipeRefreshLayout(): SwipeRefreshLayout
    abstract fun initRecyclerView()
    abstract fun initRefreshLayout()
}