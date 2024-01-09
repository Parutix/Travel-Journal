package com.example.traveljournal

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    private lateinit var themeRadioGroup: RadioGroup
    private lateinit var lightThemeRadioButton: RadioButton
    private lateinit var darkThemeRadioButton: RadioButton
    private lateinit var notificationsSwitch: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        themeRadioGroup = view.findViewById(R.id.themeRadioGroup)
        lightThemeRadioButton = view.findViewById(R.id.lightThemeRadioButton)
        darkThemeRadioButton = view.findViewById(R.id.darkThemeRadioButton)
        notificationsSwitch = view.findViewById(R.id.notificationsSwitch)

        setInitialTheme()

        setUpThemeListener()
        setUpNotificationsListener()

        return view
    }

    private fun setInitialTheme() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> lightThemeRadioButton.isChecked = true
            Configuration.UI_MODE_NIGHT_YES -> darkThemeRadioButton.isChecked = true
        }
    }

    private fun setUpThemeListener() {
        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.lightThemeRadioButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    recreateActivity()
                }
                R.id.darkThemeRadioButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    recreateActivity()
                }
            }
        }
    }

    private fun recreateActivity() {
        activity?.recreate()
    }

    private fun setUpNotificationsListener() {
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
            } else {
            }
        }
    }
}
