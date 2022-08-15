package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseRecyclerViewModel
import com.example.android.http.bean.SystemCategoryBean
import com.example.android.repository.SystemCategoryRepository

class SystemCategoryViewModel : BaseRecyclerViewModel() {
    private val systemCategoryRepository by lazy { SystemCategoryRepository() }
    val categories: MutableLiveData<List<SystemCategoryBean>> = MutableLiveData()

    fun getSystemCategory() {
        launch(block = {
            setRefreshStatus(true)
//            categories.value =
//                ArrayList<SystemCategoryBean>().apply {
//                    addAll(systemCategoryRepository.getSystemCategory())
//                }
            setRefreshStatus(false)
        })
    }
}