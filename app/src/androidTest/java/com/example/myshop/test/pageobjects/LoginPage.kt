package com.example.myshop.test.pageobjects

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector


object LoginPage {
    private var device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private const val SEND_VERIFICATION_CODE_BTN_TEXT = "SEND VERIFICATION CODE"
    private const val SKIP_BTN_ID="com.example.myshop:id/btn_skip"
    private const val MOBILE_NUMBER_FIELD_TEXT = "SEND VERIFICATION CODE"
    private const val SIGN_IN_WITH_GOOGLE_BTN_TEXT = "SIGN IN WITH GOOGLE"
    private const val CHOOSE_AN_ACCOUNT_TEXT = "chooseAnAccount"
    private const val USER_ACCOUNT_ID = "com.google.android.gms:id/account_display_name"
    private const val SEARCH_BOX_ID = "com.example.myshop:id/edt_txt_search_home"


    public fun sendVerificationCode(): UiObject? {
        var sendVerificationCodeBtn =
            device.findObject(UiSelector().text(SEND_VERIFICATION_CODE_BTN_TEXT))
        return sendVerificationCodeBtn
    }

    public fun skipBtn() {
        var skipBtn=device.findObject(UiSelector().resourceId(SKIP_BTN_ID))
        skipBtn.click()
    }

    public fun mobileNumberField(number: String) {
        var mobileNumberField = device.findObject(UiSelector().text(MOBILE_NUMBER_FIELD_TEXT))
        mobileNumberField.setText(number)
    }

    public fun signInWithGoogle() {
        var signInWithGoogleBtn = device.findObject(UiSelector().text(SIGN_IN_WITH_GOOGLE_BTN_TEXT))
        signInWithGoogleBtn.click()
    }

    public fun chooseAnAccount(): UiObject? {
        var chooseAnAccountText = device.findObject(UiSelector().text(CHOOSE_AN_ACCOUNT_TEXT))
        return chooseAnAccountText
    }

    public fun userAccount() {
        var userAccount = device.findObject(UiSelector().resourceId(USER_ACCOUNT_ID))
        userAccount.click()
    }

    public fun searchBox(): UiObject? {
        var searchBox = device.findObject(UiSelector().resourceId(SEARCH_BOX_ID))
        return searchBox
    }

    public fun pressBackBtn() {
        device.pressBack()
    }
}