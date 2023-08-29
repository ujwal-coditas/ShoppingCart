package com.example.myshop.viewmodel.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.model.CategoryListResponse
import com.example.myshop.model.ProductListResponse
import kotlinx.coroutines.launch
import com.example.myshop.repositories.ApiRepository
import com.example.myshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class CategoryListViewModel @Inject constructor (private val apiRepository: ApiRepository) :
    ViewModel() {
    private val categoryDetailLiveData = MutableLiveData<Resource<CategoryListResponse>>()
    private val categoryValuesList: LiveData<Resource<CategoryListResponse>> =
        categoryDetailLiveData

    fun getCategory(): LiveData<Resource<CategoryListResponse>> {
        viewModelScope.launch {
            categoryDetailLiveData.postValue(Resource.Loading())
            try {
                val categoryList = apiRepository.getCategoryList()
                if (categoryList.body() != null) {
                    categoryDetailLiveData.postValue(Resource.Success(categoryList.body()))
                }

            } catch (e: Exception) {
                categoryDetailLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return categoryValuesList
    }




}