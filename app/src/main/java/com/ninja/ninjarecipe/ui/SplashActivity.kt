package com.ninja.ninjarecipe.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ninja.ninjarecipe.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

     Handler(Looper.getMainLooper()).postDelayed(object: Runnable{
         override fun run() {
             val intent = Intent(this@SplashActivity, HomeActivity::class.java)
             startActivity(intent)
             finish()
         }
     }, 1000)
        }
    }