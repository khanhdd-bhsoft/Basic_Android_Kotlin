package com.example.myapplication.Fragments

import android.hardware.biometrics.BiometricManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.myapplication.R
import com.example.myapplication.controller.SharePrefManager
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var preferencesManager: SharePrefManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesManager = SharePrefManager(requireContext())
        val localizationPref = findPreference<SwitchPreferenceCompat>("localization")
        val themesPref = findPreference<SwitchPreferenceCompat>("themes")


        // read from shared preferences
        val currentLanguage = preferencesManager.language
        val currentTheme = preferencesManager.themes
        println("TEST FROM HERE themes: "+currentTheme)
        println("TEST FROM HERE language: "+currentLanguage)
        localizationPref?.isChecked = currentLanguage == "en"
        themesPref?.isChecked = currentTheme
        getValue(currentTheme,currentLanguage)

        //
        localizationPref?.setOnPreferenceChangeListener { preference, newValue ->
            val isEnglishLanguage = newValue as Boolean
            if(isEnglishLanguage)
            {
                setLocale("en")
            }else{
                setLocale("vi")
            }
            true
        }

        themesPref?.setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener { preference, newValue ->
            val isLightMode = newValue as Boolean
            setThemesMode(isLightMode)
            true
        })
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }
    private fun getValue(currentTheme: Boolean,currentLanguage: String?)
    {
        applyThemesMode(currentTheme)
        if(currentLanguage!=null)
        {
            applyLanguage(currentLanguage)
        }
    }
    private fun setLocale(languageCode: String) {
        preferencesManager.language = languageCode
        applyLanguage(languageCode)
    }
    private fun applyLanguage(languageCode: String)
    {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
    private fun setThemesMode(isLightMode: Boolean)
    {
        preferencesManager.themes = isLightMode
        applyThemesMode(isLightMode)
    }
    private fun applyThemesMode(isLightMode: Boolean)
    {
        if (isLightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}