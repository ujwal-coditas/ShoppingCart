package com.example.myshop.test.pageobjects

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

object ValidationPage {
    private var device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private const val OTP_TEXT_FIELD_ID = "com.example.myshop:id/edt_enter_otp"
    private const val VALIDATE_BTN_TEXT = "VALIDATE"
    private const val CHANGE_PHONE_NUMBER_BTN_TEXT = "Change Phone Number"

    fun enterOtp(otp: String) {
        var otpTextField = device.findObject(UiSelector().resourceId(OTP_TEXT_FIELD_ID))
        otpTextField.setText(otp)
    }

    fun validateBtn(): UiObject? {
        var validateBtn = device.findObject(UiSelector().text(VALIDATE_BTN_TEXT))
        return validateBtn
    }

    fun changePhoneNumber(): UiObject? {
        var changePhoneNumberBtn = device.findObject(UiSelector().text(CHANGE_PHONE_NUMBER_BTN_TEXT))
        return changePhoneNumberBtn
    }
}