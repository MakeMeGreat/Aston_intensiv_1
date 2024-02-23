package com.example.aston_intentsiv_26

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


class MusicService : Service() {
    private lateinit var player: MusicPlayer
    override fun onCreate() {
        player = MusicPlayer(applicationContext)
        super.onCreate()

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stop()
            Actions.NEXT.toString() -> player.playNextMusic()
            Actions.PREVIOUS.toString() -> player.playPreviousMusic()
        }

        return START_STICKY
    }

    private fun stop() {
        player.playerStop()
        stopSelf()
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Music service is active")
            .setContentText("Ludovico Einaudi time")
            .setOngoing(true)
            .build()

        player.startOrPauseMusic()
        startForeground(1, notification)
    }

    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }

    enum class Actions {
        START, STOP, NEXT, PREVIOUS
    }
}