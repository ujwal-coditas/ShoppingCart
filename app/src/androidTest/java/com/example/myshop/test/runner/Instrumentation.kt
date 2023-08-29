package com.example.myshop.test.runner

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith
import java.io.File

@CucumberOptions(
    features = ["features"],
    dryRun = false,
    glue = ["com.example.myshop.test.steps"],
    tags = ["@catalogue"],
    monochrome = true
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class Instrumentation : CucumberAndroidJUnitRunner() {
    private companion object {
        const val cucumber = "cucumber"
        const val separator = "--"
        const val SLASH = "/"
        const val ARG_PLUGIN = "plugin"
    }

    override fun onCreate(arguments: Bundle?) {
        arguments?.putString(
            ARG_PLUGIN, getPluginConfigurationString()
        )
        File(getAbsoluteFilesPath()).mkdirs()
        super.onCreate(arguments)
    }

    /**
     * Since we want to checkout the external storage directory programmatically, we create the plugin configuration
     * here, instead of the [CucumberOptions] annotation.
     *
     * @return the plugin string for the configuration, which contains XML, HTML and JSON paths
     */
    private fun getPluginConfigurationString(): String {
        return "junit:" + getCucumberXml() + separator +
                "html:" + getCucumberHtml() + separator +
                "json:" + getCucumberJson()
    }

    private fun getCucumberHtml(): String {
        return getAbsoluteFilesPath() + SLASH + cucumber + ".html"
    }

    private fun getCucumberXml(): String {
        return getAbsoluteFilesPath() + SLASH + cucumber + ".xml"
    }

    private fun getCucumberJson(): String {
        return getAbsoluteFilesPath() + SLASH + cucumber + ".json"
    }

    /**
     * The path which is used for the report files.
     *
     * @return the absolute path for the report files
     */
    private fun getAbsoluteFilesPath(): String {

        val directory = targetContext.filesDir
        val reportsDir = File(directory, "reports")
        return reportsDir.absolutePath

    }

    override fun onStart() {
        super.onStart()
        waitForIdleSync()
    }
}

