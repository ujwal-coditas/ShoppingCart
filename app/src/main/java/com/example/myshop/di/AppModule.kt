package com.example.myshop.di

import android.provider.SyncStateContract
import com.example.myshop.api.FakeStoreAPI
import com.example.myshop.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getBaseUrl(): String = Constants.BASE_URL


    @Singleton
    @Provides
    fun getRetrofit(baseUrl:String): Retrofit =  Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun getPostRequest(retrofit:Retrofit): FakeStoreAPI= retrofit.create(FakeStoreAPI::class.java)
}