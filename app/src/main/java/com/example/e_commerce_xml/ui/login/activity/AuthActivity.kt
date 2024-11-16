package com.example.e_commerce_xml.ui.login.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.e_commerce_xml.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initNavComponent()
    }
    fun initNavComponent(){
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController
    }
}