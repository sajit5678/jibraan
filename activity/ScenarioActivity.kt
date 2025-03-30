/*
 * Copyright (C) 2023 Kevin Buzeau
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.buzbuz.smartautoclicker.activity

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.buzbuz.smartautoclicker.R
import com.buzbuz.smartautoclicker.core.processing.data.CoordinatePoints
import kotlin.system.exitProcess

/**
 * Entry point activity for the application.
 * Shown when the user clicks on the launcher icon for the application, this activity will displays the list of
 * available scenarios, if any.
 */
class ScenarioActivity : AppCompatActivity() {

    /** ViewModel providing the click scenarios data to the UI. */
    private val scenarioViewModel: ScenarioViewModel by viewModels()
    val cordinates = CoordinatePoints()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val androidId = getAndroidId(this)
        printShortcuts()
//        val atombi = "c9063bc376f44228"
        val kalam = "7c224c2fc5271823"
        val sajitnote = "85e7b944b5f6c6f8"
        val apik = "aa1df679cba8a261"
        val mypro = "349811c6dfcca4ed"
        val ibungo = "88ba7d7bade7fd4d"
        val abul1 = "cc77d51af1d05fa1"
//        12gb
        val abul2 = "74dc694bb6ba26c2"
        val asik = "cc05983fd3eadcfd"
        val anuwar = "06c4031f67c4e24b"
        val nashir = "a305c28346712335"
        val albas = "04250630ff9d4f93"
        val marym = "ab90d5db65127609"
        val jabir = "2d7781bae049bbcd"
        val aheshan = "dfe6dff5ec5efa03"
//        if (androidId == cordinates.androidid) {
//            setContentView(R.layout.activity_scenario)
//        } else {
//            exitProcess(0)
//        }
        setContentView(R.layout.activity_scenario)

//        setContentView(R.layout.activity_scenario)
        scenarioViewModel.stopScenario()
    }

    fun getAndroidId(context: Context): String {
        val contentResolver: ContentResolver = context.contentResolver
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun printShortcuts() {
        val shortcutIntent = Intent(Intent.ACTION_CREATE_SHORTCUT)
        val shortcuts = packageManager.queryIntentActivities(shortcutIntent, 0)
        shortcuts.forEach {
            println("name = ${it.activityInfo.name}, label = ${it.loadLabel(packageManager)}")
        }
    }

}
