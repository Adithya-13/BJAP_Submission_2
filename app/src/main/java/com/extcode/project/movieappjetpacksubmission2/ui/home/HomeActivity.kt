package com.extcode.project.movieappjetpacksubmission2.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.extcode.project.movieappjetpacksubmission2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tab.setupWithViewPager(activityHomeBinding.viewPager)
    }
}