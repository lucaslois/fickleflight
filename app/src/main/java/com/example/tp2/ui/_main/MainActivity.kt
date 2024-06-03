package com.example.tp2.ui._main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

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
        leftNavView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)

        setSupportActionBar(topToolbar)
        navController = findNavController(R.id.navGraphFragmentActivityMain)

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = fragmentsNavigation,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        topToolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)


        /*
        //Configuro NavigationView
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        leftNavView.setupWithNavController(navController)


        toggle = ActionBarDrawerToggle(
            this, drawerLayout, topToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        val btnSearch = findViewById<Button>(R.id.btnSearchFlights)
        btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_searchFlightFragment_to_flightListFragment)
        }

         */

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->

        NavigationUI.setupWithNavController(bottomNavView, navController)
        NavigationUI.setupWithNavController(leftNavView, navController)

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

    /*

    override fun onSupportNavigateUp(): Boolean {
        //Fuezo al boton de navegación de la toolbar que solo abra el menú Drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

     */
}