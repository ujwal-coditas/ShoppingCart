package com.example.myshop.test.steps

import com.example.myshop.test.pageobjects.LoginPage
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class SignInWithGoogleSteps {
    companion object
    {
        private val login=LoginPage
    }

    @When("^User clicks on SIGN In WITH GOOGLE button$")
    fun user_clicks_on_sign_in_with_google_button() {
        login.signInWithGoogle()
    }

    @Then("^Choose an account window should pop up$")
    fun choose_an_account_window_should_pop_up() {
        login.chooseAnAccount()?.exists()
    }

    @When("^User select google account by clicking on it$")
    fun user_select_google_account_by_clicking_on_it() {
        login.userAccount()
    }

    @Then("^User should redirected to Home page$")
    fun user_should_redirected_to_home_page() {
        login.searchBox()?.waitForExists(5000)
        login.searchBox()?.let { assert(it.exists()) }
    }

}

