package com.example.consentration.ui.timer

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.consentration.R

class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference)
    }
}