package com.ajkumar.musicplayer.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    private var _progress: MutableStateFlow<Long> = MutableStateFlow(0L)
    val progress: StateFlow<Long> = _progress

    private var _player: MutableStateFlow<ExoPlayer> =
        MutableStateFlow(ExoPlayer.Builder(context).build())
    val player: StateFlow<ExoPlayer> = _player

    fun setAudio(uri: Uri) {
        val mediaItem = MediaItem.fromUri(uri)
        player.value.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    fun startTrackingProgress() {

    }

    fun playPause() = if (_player.value.isPlaying) _player.value.pause() else _player.value.play()

    fun releasePlayer() = _player.value.release()

    fun seekTo(timeMs: Long) = _player.value.seekTo(timeMs)

}