package com.example.myshop.repositories

import androidx.lifecycle.MutableLiveData
import com.example.myshop.model.UserGoogle
import com.example.myshop.utils.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class GoogleAuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<Resource<UserGoogle>> {
        val authenticatedUserMutableLiveData = MutableLiveData<Resource<UserGoogle>>()

        firebaseAuth.signInWithCredential(googleAuthCredential).addOnCompleteListener { authTask ->
            if (authTask.isSuccessful) {
                val isNewUser = authTask.result?.additionalUserInfo?.isNewUser
                val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val uid = firebaseUser.uid
                    val name = firebaseUser.displayName
                    val email = firebaseUser.email
                    val user = UserGoogle(uid = uid, name = name, email = email)
                    user.isNew = isNewUser
                    authenticatedUserMutableLiveData.value = Resource.Success(user)

                }
            } else {
                authenticatedUserMutableLiveData.value = authTask.exception?.message?.let {
                    Resource.Error(it)
                }

            }

        }
        return authenticatedUserMutableLiveData
    }
}