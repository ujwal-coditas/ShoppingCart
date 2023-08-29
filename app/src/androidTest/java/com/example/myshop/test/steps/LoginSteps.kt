package com.example.myshop.test.steps

import androidx.test.core.app.ActivityScenario
import com.example.myshop.test.pageobjects.GetStartedPage
import com.example.myshop.test.pageobjects.LoginPage
import com.example.myshop.test.pageobjects.ValidationPage
import com.example.myshop.views.activities.GetStartedActivity
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class LoginSteps {
    companion object{
        private val login=LoginPage
        private val getStarted=GetStartedPage
        private const val MOBILE_NO="9876543210"
    }

    @Given("^User is on Get Started page$")
    fun user_is_on_get_started_page() {
        ActivityScenario.launch(GetStartedActivity::class.java)
        getStarted.myShopTitle()?.let { assert(it.exists()) }
    }

    @When("^User click on GET STARTED BUTTON$")
    fun user_click_on_get_started_button() {
        getStarted.getStarted()?.click()
    }

    @Then("^User should redirected to login page$")
    fun user_should_redirected_to_login_page() {
        login.sendVerificationCode()?.exists()?.let { assert(it) }
    }

    @Given("^User is on login page$")
    fun user_is_on_login_page() {
        login.sendVerificationCode()?.exists()?.let { assert(it) }
    }

    @When("^User clicks on skip button$")
    fun user_clicks_on_skip_button() {
        login.skipBtn()
    }

    @When("^User click on back button$")
    fun user_click_on_back_button() {
        login.pressBackBtn()
    }

    @When("^user enter mobile number$")
    fun user_enter_mobile_number() {
       login.mobileNumberField(MOBILE_NO)
    }

    @And("^User click on SEND VERIFICATION CODE button$")
    fun user_click_on_send_verification_code_button() {
        login.sendVerificationCode() ?.click()
    }

    @Then("^User should redirected to validation page$")
    fun user_should_redirected_to_validation_page() {
        ValidationPage.validateBtn()?.let{ assert(it.exists())}
    }


}