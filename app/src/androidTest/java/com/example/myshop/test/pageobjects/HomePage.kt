package com.example.myshop.test.pageobjects


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*


open class HomePage {
    companion object {
        private var device: UiDevice =
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        var searchBoxId = "com.example.myshop:id/edt_txt_search_home"
        var homeTabText = "Home"
        var catalogueTabText = "Catalogue"
        var favouriteTabText = "Favorite"
        var profileTabText = "Profile"

    }

    fun getSearchBox(): UiObject? {
        return device.findObject(UiSelector().resourceId(searchBoxId))
    }

    fun getHomeTab(): UiObject? {
        return device.findObject(UiSelector().text(homeTabText))
    }

    fun getCatalogueTab(): UiObject? {
        return device.findObject(UiSelector().text(catalogueTabText))
    }

    fun getFavouriteTab(): UiObject? {
        return device.findObject(UiSelector().text(favouriteTabText))
    }

    fun getProfileTab(): UiObject? {
        return device.findObject(UiSelector().text(profileTabText))

    }

    fun scrollDown() {
        val scrollView = UiScrollable(
            UiSelector().scrollable(true)
        )
        scrollView.scrollToEnd(5)

    }


}