package com.example.myshop.viewmodel.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.model.CategoryListResponse
import com.example.myshop.model.ProductListItem
import com.example.myshop.model.ProductListResponse
import kotlinx.coroutines.launch
import com.example.myshop.repositories.ApiRepository
import com.example.myshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CatalogueListViewModel @Inject constructor (private val apiRepository: ApiRepository) :
    ViewModel() {
    private val categoryDetailLiveData = MutableLiveData<Resource<CategoryListResponse>>()
    private val catalogueValuesList: LiveData<Resource<CategoryListResponse>> = categoryDetailLiveData

    private val _productByCategoryLiveData = MutableLiveData<Resource<ProductListResponse>>()
     val productByCategoryLiveData: LiveData<Resource<ProductListResponse>> =
        _productByCategoryLiveData
    
    private val filteredProductByPrice = ProductListResponse()


    fun getCatalouge() : LiveData<Resource<CategoryListResponse>> {

        viewModelScope.launch {
            categoryDetailLiveData.postValue(Resource.Loading())
            try {
                val catalogueList = apiRepository.getCategoryList()
                if (catalogueList.body() != null) {
                    categoryDetailLiveData.postValue(Resource.Success(catalogueList.body()))
                }
            } catch (e: Exception) {
                categoryDetailLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return catalogueValuesList
    }
    fun getProductByPriceFilter(productCategory:String,startRange:Double,endRange:Double): LiveData<Resource<ProductListResponse>> {
        viewModelScope.launch {
            _productByCategoryLiveData.postValue(Resource.Loading())
            try {
                val productByCategoryList = apiRepository.getProductsByCategory(productCategory)
                if (productByCategoryList.body() != null) {
                    productByCategoryList.body().let {
                        filteredProductByPrice.clear()
                        for (product in it!!) {
                            if (product.price in startRange..endRange) {
                                filteredProductByPrice.add(product)
                            }
                        }
                        _productByCategoryLiveData.postValue(Resource.Success(filteredProductByPrice))
                    }
                }

            } catch (e: Exception) {
                _productByCategoryLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return _productByCategoryLiveData
    }

}