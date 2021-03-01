package com.example.newsappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import com.example.newsappmvvm.databinding.ActivityMainBinding;

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // all the MainActivity class needs to do is inflate the layout so that the fragment is displayed in the view
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // findNavController() will crash if used on its own in onCreate
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        // responsible for setting our actionbar to our toolbar, not required if using default actionbar
        // uses the nav_graph fragment labels as headers at run-time. Change labels to alter
        setSupportActionBar(toolbar)
        // sets our actionbar as our nav controller
        // works for toolbar and default actionbar
        setupActionBarWithNavController(navController)
    }

    // without this, the up button on our toolbar won't do anything
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}