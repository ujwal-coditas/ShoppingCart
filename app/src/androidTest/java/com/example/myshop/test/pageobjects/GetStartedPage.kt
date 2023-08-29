package com.example.myshop.test.pageobjects

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

object GetStartedPage {
    private var device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private const val MY_SHOP_TEXT = "MyShop"
    private const val GET_STARTED_BTN_ID = "com.example.myshop:id/btn_get_started"

    public fun myShopTitle(): UiObject? {
        var myShopText = device.findObject(UiSelector().text(MY_SHOP_TEXT))
        return myShopText
    }

    public fun getStarted(): UiObject? {
        var getStaredBtn = device.findObject(UiSelector().resourceId(GET_STARTED_BTN_ID))
        return getStaredBtn
    }
}