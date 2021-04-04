package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.capstoneproject.databinding.ActivityBookmarkBinding

class BookmarkActivity : AppCompatActivity() {

    private var _binding : ActivityBookmarkBinding? = null
    private val binding get() = _binding!!
    private val navHostController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_bookmark) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(navHostController)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            "Reselect blocked."
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(navHostController.currentBackStackEntry == null)
            finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}