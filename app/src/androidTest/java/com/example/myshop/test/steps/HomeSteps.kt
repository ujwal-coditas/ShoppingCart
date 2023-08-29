package com.example.myshop.test.steps

import android.util.Log
import androidx.test.core.app.ActivityScenario
import com.example.myshop.test.pageobjects.HomePage
import com.example.myshop.views.activities.MainActivity
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then


class HomeSteps {
    var homePage = HomePage()

    @Given("^User is on home page$")
    fun user_is_on_home_page() {
        Log.d("homeLog", "launchApp")
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Then("^Verify search box$")
    fun verify_search_box() {
        Log.d("homeLog", "searchBoxVerification")
        homePage.getSearchBox()?.isEnabled
    }

    @And("^Verify bottom bar tabs$")
    fun verify_bottom_bar_tabs() {
        Log.d("homeLog", "bottomBarTabsVerification")
        homePage.apply {
            getHomeTab()?.isClickable
            getCatalogueTab()?.click()
            getFavouriteTab()?.isClickable
            getProfileTab()?.isClickable
        }
    }

    @And("^verify page vertical scroll$")
    fun verify_page_vertical_scroll() {
        Log.d("homeLog", "verticalScrollVerification")
        homePage.scrollDown()

    }

}