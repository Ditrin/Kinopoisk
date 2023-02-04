package com.example.cinemasearch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cinemasearch.R
import com.example.cinemasearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
    }

//    fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
//        replace(R.id.fragmentContainer, fragment)
//        commit()
//    }
}