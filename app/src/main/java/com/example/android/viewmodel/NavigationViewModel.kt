package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseRecyclerViewModel
import com.example.android.http.bean.NavigationBean
import com.example.android.repository.NavigationRepository

class NavigationViewModel : BaseRecyclerViewModel() {
    private val navigationRepository by lazy { NavigationRepository() }
    val navigation: MutableLiveData<List<NavigationBean>> = MutableLiveData()

    fun getNavigation() {
        launch(block = {
            setRefreshStatus(true)
//            navigation.value = mutableListOf<NavigationBean>().apply {
//                addAll(navigationRepository.getNavigation())
//            }
            setRefreshStatus(false)
        })
    }
}