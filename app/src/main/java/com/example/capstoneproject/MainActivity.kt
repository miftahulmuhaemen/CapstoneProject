package com.example.capstoneproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.capstoneproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val navHostController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setupWithNavController(navHostController)

        binding.tvBookmarks.setOnClickListener {
            val uri = Uri.parse("capstoneproject://bookmark")
            startActivity(Intent(Intent(Intent.ACTION_VIEW, uri)))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (navHostController.currentBackStackEntry == null)
            finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}