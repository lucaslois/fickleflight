package com.example.tp2.ui._main

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.tp2.R
import com.example.tp2.data.network.flights.FlightService
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var topToolbar: MaterialToolbar
    private lateinit var leftNavView: NavigationView

    private val fragmentsNavigation = setOf(
        R.id.homeFragment,
        R.id.searchFlightFragment,
        R.id.offersFragment,
        R.id.profileFragment,
        R.id.flightListFragment,
        R.id.settingsFragment
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        topToolbar = findViewById(R.id.customToolbar)
        bottomNavView = findViewById(R.id.bottomNav)
        leftNavView = findViewById(R.id.navigation_view)

        setSupportActionBar(topToolbar)
        navController = findNavController(R.id.navGraphFragmentActivityMain)

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = fragmentsNavigation,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        topToolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
        NavigationUI.setupWithNavController(leftNavView, navHostFragment.navController)

            when (destination.id) {
                R.id.searchFlightFragment,
                R.id.offersFragment,
                R.id.flightListFragment,
                R.id.settingsFragment
                -> {
                    bottomNavView.visibility = BottomNavigationView.VISIBLE
                    topToolbar.visibility = MaterialToolbar.VISIBLE
                }


                else -> {
                    bottomNavView.visibility = BottomNavigationView.VISIBLE
                    topToolbar.visibility = View.GONE
                }
            }
        }
    }
}