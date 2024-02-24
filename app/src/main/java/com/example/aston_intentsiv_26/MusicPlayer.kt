package com.example.aston_intentsiv_26

import android.content.Context
import android.media.MediaPlayer

class MusicPlayer(private val context: Context) : MediaPlayer() {

    private val songs =
        arrayOf(R.raw.four_dimensions, R.raw.the_crane_dance, R.raw.divenire, R.raw.choros)

    private var currentMusic = 0

    private var player: MediaPlayer = create(context, songs[currentMusic])

    fun startOrPauseMusic() {
        if (!player.isPlaying)
            player.start()
        else
            player.pause()
    }

    fun playerStop() {
        player.reset()
    }

    fun playNextMusic() {
        player.stop()
        player = create(context, getNextMusic())
        player.start()
    }

    fun playPreviousMusic() {
        player.stop()
        player = create(context, getPreviousMusic())
        player.start()
    }

    private fun getPreviousMusic(): Int {
        currentMusic--
        if (currentMusic < 0)
            currentMusic = songs.size - 1
        return songs[currentMusic]

    }

    private fun getNextMusic(): Int {
        currentMusic++
        currentMusic %= songs.size
        return songs[currentMusic]
    }
}