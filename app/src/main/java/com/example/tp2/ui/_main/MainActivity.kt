package com.example.tp2.ui._main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.tp2.R
import com.example.tp2.databinding.ActivityMainBinding
import com.example.tp2.ui.home.HomeFragment
import com.example.tp2.ui.searchFlight.SearchFlightFragmentDirections
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var topToolbar: MaterialToolbar
    private lateinit var leftNavView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityMainBinding

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomNav
        leftNavView = binding.navigationView
        drawerLayout = binding.drawerLayout
        topToolbar = binding.contentMainInclude.customToolbar

        setSupportActionBar(topToolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navGraphFragmentActivityMain) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = fragmentsNavigation,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        topToolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->

            NavigationUI.setupWithNavController(bottomNavView, navController)
            NavigationUI.setupWithNavController(leftNavView, navController)

            when (destination.id) {
                R.id.searchFlightFragment -> configureToolbarForSearchFlight()
                R.id.offersFragment -> configureToolbarForOffers()
                R.id.flightListFragment -> configureToolbarForFlightList()
                R.id.detailsFragment -> topToolbar.visibility = View.GONE
                R.id.profileFragment -> topToolbar.visibility = View.GONE
                else -> configureToolbarForHome()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun configureToolbarForSearchFlight() {
        topToolbar.visibility = View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleLogo).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleSearchResults).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarProfile).visibility =
            View.GONE

        val fragmentTitleTextView =
            binding.contentMainInclude.customToolbar.findViewById<TextView>(R.id.topToolbarTitleText)
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarIcon).visibility =
            View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarIcon).visibility =
            View.VISIBLE

        fragmentTitleTextView.visibility = View.VISIBLE
        fragmentTitleTextView.text = "Search Flight"
        val leftButton =
            binding.contentMainInclude.customToolbar.findViewById<ImageView>(R.id.leftTopToolbarContent)

        leftButton.setImageDrawable(resources.getDrawable(R.drawable.back_button, null))

        leftButton.setOnClickListener {
            val action = SearchFlightFragmentDirections.actionSearchFlightFragmentToHomeFragment()
            navController.navigate(action)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun configureToolbarForFlightList() {
        topToolbar.visibility = View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleLogo).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarProfile).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleText).visibility =
            View.GONE

        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarIcon).visibility =
            View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleSearchResults).visibility =
            View.VISIBLE

        val leftButton =
            binding.contentMainInclude.customToolbar.findViewById<ImageView>(R.id.leftTopToolbarContent)

        leftButton.setImageDrawable(resources.getDrawable(R.drawable.back_button, null))

        leftButton.setOnClickListener {
            navController.navigate(R.id.action_flightListFragment_to_searchFlightFragment)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun configureToolbarForOffers() {
        topToolbar.visibility = View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleLogo).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleSearchResults).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarProfile).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarIcon).visibility =
            View.GONE

        val fragmentTitleTextView =
            binding.contentMainInclude.customToolbar.findViewById<TextView>(R.id.topToolbarTitleText)

        fragmentTitleTextView.visibility = View.VISIBLE
        fragmentTitleTextView.text = "Offers"
        val leftButton =
            binding.contentMainInclude.customToolbar.findViewById<ImageView>(R.id.leftTopToolbarContent)

        leftButton.setImageDrawable(resources.getDrawable(R.drawable.back_button, null))

        leftButton.setOnClickListener(View.OnClickListener {
            navController.navigateUp()
        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun configureToolbarForHome() {
        topToolbar.visibility = View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<TextView>(R.id.topToolbarTitleText).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleSearchResults).visibility =
            View.GONE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarIcon).visibility =
            View.GONE

        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.topToolbarTitleLogo).visibility =
            View.VISIBLE
        binding.contentMainInclude.customToolbar.findViewById<View>(R.id.rightTopToolbarProfile).visibility =
            View.VISIBLE

        val leftButton =
            binding.contentMainInclude.customToolbar.findViewById<ImageView>(R.id.leftTopToolbarContent)

        leftButton.setImageDrawable(resources.getDrawable(R.drawable.ic_hamburger_menu, null))


        leftButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(leftNavView)) {
                drawerLayout.closeDrawer(leftNavView)
            } else {
                drawerLayout.openDrawer(leftNavView)
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