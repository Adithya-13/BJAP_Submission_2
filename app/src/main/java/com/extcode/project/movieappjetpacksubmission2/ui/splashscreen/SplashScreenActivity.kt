package com.extcode.project.movieappjetpacksubmission2.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.extcode.project.movieappjetpacksubmission2.R
import com.extcode.project.movieappjetpacksubmission2.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashScreenTime)

    }
}