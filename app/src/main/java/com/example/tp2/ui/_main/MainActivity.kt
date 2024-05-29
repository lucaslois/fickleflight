package com.example.tp2.ui._main

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tp2.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavView = findViewById(R.id.bottomNav)

        val toolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
        NavigationUI.setupWithNavController(toolbar, navHostFragment.navController)

    }
}