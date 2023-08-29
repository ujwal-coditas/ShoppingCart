package com.example.myshop.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.R
import com.example.myshop.databinding.ActivityEnterPhoneBinding
import com.example.myshop.repositories.GoogleAuthRepository
import com.example.myshop.utils.Constants
import com.example.myshop.utils.Resource
import com.example.myshop.viewmodel.googleauth.GoogleAuthViewModel
import com.example.myshop.viewmodel.googleauth.GoogleAuthModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import androidx.lifecycle.Observer


class EnterPhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterPhoneBinding
    private lateinit var googleAuthViewModel: GoogleAuthViewModel
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnterPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSendCode.setOnClickListener {
            val intent = Intent(this, OtpVerificationActivity::class.java)
            startActivity(intent)
        }
        binding.btnSkip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val googleAuthRepository = GoogleAuthRepository()

        googleAuthViewModel = ViewModelProvider(
            this,
            GoogleAuthModelFactory(googleAuthRepository)
        )[GoogleAuthViewModel::class.java]

        binding.btnGoogleSignIn.setOnClickListener {
            signInUsingGoogle()
        }
        initGoogleSignInClient()
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private fun signInUsingGoogle() {
        val signInGoogleIntent = googleSignInClient.signInIntent
        startActivityForResult(signInGoogleIntent, Constants.RED_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RED_CODE) {
            val taskForResult = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val accountInfo = taskForResult.getResult(ApiException::class.java)!!
                getGoogleAuthCredential(accountInfo)
            } catch (e: ApiException) {
                Toast.makeText(
                    this,
                    "${Constants.AUTH_ERROR_MEG} ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getGoogleAuthCredential(account: GoogleSignInAccount) {
        val googleTokeId = account.idToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokeId, null)
        signInWithGoogleAuthCredential(googleAuthCredential)
    }

    private fun signInWithGoogleAuthCredential(googleAuthCredential: AuthCredential) {
        googleAuthViewModel.signInWithGoogle(googleAuthCredential)
            .observe(this, Observer { authenticatedUser ->
                when (authenticatedUser) {
                    is Resource.Error -> {
                        hideProgressBar()
                        authenticatedUser.errorMessage?.let {
                            Toast.makeText(
                                this,
                                "${Constants.AUTH_ERROR_MEG} $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        if (authenticatedUser.data != null) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })
    }

    private fun showProgressBar() {
        binding.progressBarAuth.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarAuth.visibility = View.INVISIBLE
    }
}
