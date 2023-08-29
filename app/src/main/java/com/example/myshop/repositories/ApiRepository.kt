package com.example.myshop.repositories

import com.example.myshop.api.FakeStoreAPI
import javax.inject.Inject

class ApiRepository @Inject constructor (private val fakeStoreAPI: FakeStoreAPI) {
    suspend fun getProductList() = fakeStoreAPI.getProductList()
    suspend fun getCategoryList() = fakeStoreAPI.getCategory()
    suspend fun getProductsByCategory(category: String) = fakeStoreAPI.getProductsByCategory(category)

}