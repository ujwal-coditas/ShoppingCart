package com.example.myshop.viewmodel.googleauth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.repositories.GoogleAuthRepository

class GoogleAuthModelFactory(private val googleAuthRepository: GoogleAuthRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GoogleAuthViewModel(googleAuthRepository) as T
    }
}