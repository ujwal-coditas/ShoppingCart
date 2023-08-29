package com.example.myshop.viewmodel.product

import androidx.lifecycle.*
import com.example.myshop.model.ProductListResponse
import kotlinx.coroutines.launch
import com.example.myshop.repositories.ApiRepository
import com.example.myshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor (private val apiRepository: ApiRepository) :
    ViewModel() {
    private val productDetailLiveData = MutableLiveData<Resource<ProductListResponse>>()
    private val productValuesList: LiveData<Resource<ProductListResponse>> = productDetailLiveData
    fun getProducts(): LiveData<Resource<ProductListResponse>> {
        viewModelScope.launch {
            productDetailLiveData.postValue(Resource.Loading())
            try {
                val productList = apiRepository.getProductList()
                if (productList.body() != null) {
                    productDetailLiveData.postValue(Resource.Success(productList.body()))
                }
            } catch (e: Exception) {
                productDetailLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return productValuesList
    }
}