package com.ajkumar.musicplayer.domain.model

import android.net.Uri

data class Audio(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Long,
    val contentUri: Uri,
    val albumArtUri: Uri? = null
)
