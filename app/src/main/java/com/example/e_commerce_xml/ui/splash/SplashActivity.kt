package com.example.e_commerce_xml.ui.splash


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.ui.login.activity.AuthActivity
import com.example.e_commerce_xml.ui.main.MainActivity
import com.example.e_commerce_xml.utils.UserDataFiled
import com.example.e_commerce_xml.utils.UserDataUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        makeStatusBarTransparentAndIconsClear()
        Handler(Looper.getMainLooper())
            .postDelayed({
               startMainActivity()
            }, 1200)
        }

    private fun startMainActivity() {
        val token = UserDataUtils().getUserData(this, UserDataFiled.TOKEN)
        if(token.isNullOrEmpty()){
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }



    private fun makeStatusBarTransparentAndIconsClear() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
    }
