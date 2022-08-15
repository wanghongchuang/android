package com.example.android.ui.widget

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android.R
import com.example.android.listener.OnSearchViewClickListener

class SearchHistoryView(context: Context) :
    ConstraintLayout(context) {
    private var listener: OnSearchViewClickListener? = null

    constructor(context: Context, listener: OnSearchViewClickListener) : this(context) {
        this.listener = listener
        LayoutInflater.from(context).inflate(R.layout.layout_search_history_view, this)

        getHotKeys()
        getSearchHistoryKeys()
    }

    // 获取搜索热词
    private fun getHotKeys() {
        listener?.getHotKeys()
    }

    // 获取本地搜索历史词
    private fun getSearchHistoryKeys() {
        listener?.getSearchHistoryKeys()
    }
}