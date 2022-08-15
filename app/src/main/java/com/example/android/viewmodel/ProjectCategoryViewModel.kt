package com.example.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseRecyclerViewModel
import com.example.android.base.BaseViewModel
import com.example.android.repository.ProjectCategoryRepository

class ProjectCategoryViewModel : BaseViewModel() {
    private val projectCategoryRepository by lazy { ProjectCategoryRepository() }
//    val categories: MutableLiveData<List<ProjectCategoryBean>> = MutableLiveData()

    fun getProjectCategory() {
//        launch(block = {
//            categories.value = projectCategoryRepository.getProjectCategory()
//        })
    }
}