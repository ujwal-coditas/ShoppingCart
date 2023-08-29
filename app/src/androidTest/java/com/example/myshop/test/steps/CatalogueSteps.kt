package com.example.myshop.test.steps

import android.util.Log
import androidx.test.core.app.ActivityScenario
import com.example.myshop.test.pageobjects.CataloguePage
import com.example.myshop.views.activities.MainActivity
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When


class CatalogueSteps {

    private var cataloguePage = CataloguePage()

    @Given("^User click on Catalogue tab$")
    fun user_click_on_catalogue_tab() {
        Log.d("cataloguePageTests", "clickOnCataloueTab")
        ActivityScenario.launch(MainActivity::class.java)
        cataloguePage.getCatalogueTab()?.click()
    }

    @And("^verify men's section$")
    fun verify_mens_section() {
        Log.d("cataloguePageTests", "clickOnMensTab")
        cataloguePage.getMensTab().click()
    }

    @When("^User apply filter by selecting max price range$")
    fun user_apply_filter_by_selecting_max_price_range() {
        cataloguePage.apply {
            getFilterButton().click()
            getSeekBarFun()
        }
    }

    @Then("^Click on result button$")
    fun click_on_result_button() {
        cataloguePage.apply {
            getResultButton().click()
            getBackButton()
        }
    }

    @When("^User click on (.+) section$")
    fun user_click_on_section(section: String?) {
        cataloguePage.getSection(section)
    }

    @And("^user select category (.+) and brand (.+)$")
    fun user_select_category_and_brand(category: String?, brand: String?) {
        cataloguePage.getCategory(category, brand)

    }

}