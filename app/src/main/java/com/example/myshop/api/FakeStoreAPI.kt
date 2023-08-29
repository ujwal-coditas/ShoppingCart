package com.example.myshop.api

import com.example.myshop.model.CategoryListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.myshop.model.ProductListResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Singleton

interface  FakeStoreAPI {

    @GET("products")
    suspend fun getProductList(): Response<ProductListResponse>


    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path(value = "category") category: String): Response<ProductListResponse>


    @GET("products/categories")
    suspend fun getCategory(): Response<CategoryListResponse>
}