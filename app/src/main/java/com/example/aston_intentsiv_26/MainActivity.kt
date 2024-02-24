package com.example.aston_intentsiv_26

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.aston_intentsiv_26.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        binding.playButton.setOnClickListener {
            Intent(applicationContext, MusicService::class.java).also {
                it.action = MusicService.Actions.START.toString()
                startService(it)
            }
        }

        binding.nextButton.setOnClickListener {
            Intent(applicationContext, MusicService::class.java).also {
                it.action = MusicService.Actions.NEXT.toString()
                startService(it)
            }
        }
        binding.stopServiceButton.setOnClickListener {
            Intent(applicationContext, MusicService::class.java).also {
                it.action = MusicService.Actions.STOP.toString()
                startService(it)
            }
        }

        binding.previousButton.setOnClickListener {
            Intent(applicationContext, MusicService::class.java).also {
                it.action = MusicService.Actions.PREVIOUS.toString()
                startService(it)
            }
        }
    }
}