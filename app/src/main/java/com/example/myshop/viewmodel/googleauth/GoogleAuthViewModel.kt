package com.example.myshop.viewmodel.googleauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.model.UserGoogle
import com.example.myshop.repositories.GoogleAuthRepository
import com.example.myshop.utils.Resource
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.launch
import java.lang.Exception

class GoogleAuthViewModel(private val googleAuthRepository: GoogleAuthRepository) :
    ViewModel() {
    private var _authenticateUserLiveData: MutableLiveData<Resource<UserGoogle>> = MutableLiveData()
    private val authenticateUserLiveData: LiveData<Resource<UserGoogle>> get() = _authenticateUserLiveData

    fun signInWithGoogle(googleAuthCredential: AuthCredential): LiveData<Resource<UserGoogle>> {
        viewModelScope.launch {
            _authenticateUserLiveData.postValue(Resource.Loading())

            try {
                _authenticateUserLiveData =
                    googleAuthRepository.firebaseSignInWithGoogle(googleAuthCredential)
            } catch (e: Exception) {
                _authenticateUserLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return authenticateUserLiveData
    }
}