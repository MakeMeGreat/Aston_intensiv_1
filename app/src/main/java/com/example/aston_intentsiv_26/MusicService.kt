package com.example.aston_intentsiv_26

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


class MusicService : Service() {
    private lateinit var player: MusicPlayer
    private lateinit var notification: Notification
    override fun onCreate() {
        player = MusicPlayer(applicationContext)
        notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Music service is active")
            .setContentText("Ludovico Einaudi time")
            .setOngoing(true)
            .build()

        super.onCreate()

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stop()
            Actions.NEXT.toString() -> next()
            Actions.PREVIOUS.toString() -> previous()
        }

        return START_STICKY
    }

    private fun stop() {
        player.playerStop()
        stopSelf()
    }

    private fun start() {
      player.startOrPauseMusic()
        startForeground(1, notification)
    }

    private fun next() {
        player.playNextMusic()
        startForeground(1, notification)
    }

    private fun previous() {
        player.playPreviousMusic()
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