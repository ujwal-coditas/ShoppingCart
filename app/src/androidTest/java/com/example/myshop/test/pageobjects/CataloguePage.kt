package com.example.myshop.test.pageobjects


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*


open class CataloguePage {

    companion object {
        private var device: UiDevice =
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        private const val CATALOGUETAB_CLASSNAME = "android.widget.LinearLayout"
        private const val ELECTRONICS_TEXT = "electronics"
        private const val JEWELERY_TEXT = "jewelery"
        private const val MENSTAB_TEXT = "men's clothing"
        private const val WOMENSTAB_TEXT = "women's clothing"

        private const val FILTERBUTTON_ID = "com.example.myshop:id/btn_filter"
        private const val SEEKBAR_CLASSNAME = "android.widget.SeekBar"
        private const val RESULTBUTTON_ID = "com.example.myshop:id/btn_result"
        private const val CATEGORYRES_ID = "com.example.myshop:id/spinner_sub_categories"
        private const val WATCHES_TEXT = "Watches"

        private const val WATCHES_INDEX = 3
        private const val BRAND_ID = "com.example.myshop:id/spinner_brand_names"
        private const val HUNNYBUNNYBRAND_INDEX = 4
        private const val ACCESSORIES_TEXT = "Accessories"
        private const val MENSFASHION_TEXT = "Mens Fashion"

        private const val GIRLSFASHION_TEXT = "Girls Fashion"
        private const val ROSEBUDS_INDEX = 5
        private const val ADVAPPAREL_INDEX = 3
        private const val BEWITCHESBOUTIQUE_INDEX = 2

        private const val WATCHES = "Watches"
        private const val ACCESSORIES = "accessories"
        private const val MENS_FASHION = "Mens fashion"
        private const val GIRS_FASHION = "Girls fashion"

        private const val ELECTRONIC = "electronic"
        private const val JEWELERY = "jewelery"
        private const val MENS = "men's"
        private const val WOMENS = "women's"


    }

    fun getCatalogueTab(): UiObject? {
        return device.findObject(UiSelector().className(CATALOGUETAB_CLASSNAME).index(1))

    }

    fun getWomensTab(): UiObject {
        return device.findObject(UiSelector().text(WOMENSTAB_TEXT))
    }

    fun getMensTab(): UiObject {
        return device.findObject(UiSelector().text(MENSTAB_TEXT))
    }

    fun getJeweleryTab(): UiObject {
        return device.findObject(UiSelector().text(JEWELERY_TEXT))
    }

    fun getElectronicsTab(): UiObject {
        return device.findObject(UiSelector().text(ELECTRONICS_TEXT))
    }

    fun getFilterButton(): UiObject {
        return device.findObject(UiSelector().resourceId(FILTERBUTTON_ID))
    }

    fun getSeekBarFun() {
        val seekBar =
            UiScrollable(UiSelector().className(SEEKBAR_CLASSNAME))
        seekBar.waitForExists(2000)
        seekBar.swipeRight(20)

    }

    fun getBackButton() {
        device.pressBack()
    }

    fun getResultButton(): UiObject {
        return device.findObject(UiSelector().resourceId(RESULTBUTTON_ID))
    }

    fun getCategories(): UiObject {
        return device.findObject(UiSelector().resourceId(CATEGORYRES_ID))
    }

    fun getWatchesCategory(): UiObject {
        return device.findObject(UiSelector().text(WATCHES_TEXT).index(WATCHES_INDEX))
    }

    fun getAccessoriesCategory(): UiObject {
        return device.findObject(UiSelector().text(ACCESSORIES_TEXT))
    }

    fun getMensFashionCategory(): UiObject {
        return device.findObject(UiSelector().text(MENSFASHION_TEXT))
    }

    fun getGirlsFashionCategory(): UiObject {
        return device.findObject(UiSelector().text(GIRLSFASHION_TEXT))
    }

    fun getBrand(): UiObject? {
        return device.findObject(UiSelector().resourceId(BRAND_ID))
    }

    fun getHunnyBunnyBrand(): UiObject? {
        return device.findObject(UiSelector().index(HUNNYBUNNYBRAND_INDEX))
    }

    fun getRosebudsBrand(): UiObject? {

        return device.findObject(UiSelector().index(ROSEBUDS_INDEX))
    }

    fun getAdventureApparelBrand(): UiObject? {
        return device.findObject(UiSelector().index(ADVAPPAREL_INDEX))
    }

    fun getBewitchedBoutiqueBrand(): UiObject? {

        return device.findObject(UiSelector().index(BEWITCHESBOUTIQUE_INDEX))
    }

    fun getSection(section: String?) {
        when (section) {
            ELECTRONIC -> {
                getElectronicsTab().click()
            }
            JEWELERY -> {
                getJeweleryTab().click()
            }
            MENS -> {
                getMensTab().click()
            }
            WOMENS -> {
                getWomensTab().click()
            }
        }
    }

    fun getCategory(category: String?, brand: String?) {
        when (category) {
            WATCHES -> {
                getCategories().click()
                getWatchesCategory().click()
                getBrand()?.click()
                getHunnyBunnyBrand()?.click()
            }
            ACCESSORIES -> {
                getCategories().click()
                getAccessoriesCategory().click()
                getBrand()?.click()
                getRosebudsBrand()?.click()
            }
            MENS_FASHION -> {
                getCategories().click()
                getMensFashionCategory().click()
                getBrand()?.click()
                getAdventureApparelBrand()?.click()
            }
            GIRS_FASHION -> {
                getCategories().click()
                getGirlsFashionCategory().click()
                getBrand()?.click()
                getBewitchedBoutiqueBrand()?.click()
            }
        }
    }
}

