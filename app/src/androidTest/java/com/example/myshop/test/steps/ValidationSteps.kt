package com.example.myshop.test.steps

import com.example.myshop.test.pageobjects.LoginPage
import com.example.myshop.test.pageobjects.ValidationPage
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class ValidationSteps {
    companion object{
        final const val OTP = "1234";
    }

    @Given("^User is on validation page$")
    fun user_is_on_validation_page() {
        ValidationPage.validateBtn()?.let { assert(it.exists()) }
    }

    @When("^User enters otp$")
    fun user_enters_otp() {
        ValidationPage.enterOtp(OTP)
    }

    @And("^Click on validate button$")
    fun click_on_validate_button() {
        ValidationPage.validateBtn()?.click()
    }

    @And("^Click on change phone number button$")
    fun click_on_change_phone_number_button() {
        ValidationPage.changePhoneNumber()?.click()
    }

    @Then("^User should redirected again to login page$")
    fun user_should_redirected_again_to_login_page() {
        LoginPage.sendVerificationCode()?.exists()?.let { assert(it) }
    }
}