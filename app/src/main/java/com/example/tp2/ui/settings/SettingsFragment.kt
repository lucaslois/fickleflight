package com.example.tp2.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.example.tp2.R
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val darkModeSwitch = view.findViewById<SwitchCompat>(R.id.dark_mode_switch)
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("DarkModeSwitch", "Modo Oscuro activado")
            } else {
                Log.d("DarkModeSwitch", "Modo Oscuro desactivado")
            }
        }
    }




}