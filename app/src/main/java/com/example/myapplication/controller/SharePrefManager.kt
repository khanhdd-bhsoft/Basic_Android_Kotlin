package com.example.myapplication.controller;

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.Constants.PreferenceKeys


class SharePrefManager(context: Context) {
   private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
    companion object {
        private const val PREFS_NAME = "app_preferences"
        private const val language_key = "language_key"
        private const val themes_mode_key = "themes_mode_key"
    }

    var language: String?
        get() = prefs.getString(language_key,"en")
        set(value) {
            prefs.edit().putString(language_key,value).apply()
        }

    var themes: Boolean
        get() = prefs.getBoolean(themes_mode_key,false)
        set(value) {
            prefs.edit().putBoolean(themes_mode_key,value).apply()
        }


}
