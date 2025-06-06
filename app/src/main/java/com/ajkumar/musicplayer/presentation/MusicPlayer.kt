package com.ajkumar.musicplayer.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ajkumar.musicplayer.domain.model.Audio
import com.ajkumar.musicplayer.presentation.screens.audioPlayer.AudioPlayerScreen
import com.ajkumar.musicplayer.presentation.theme.MusicPlayerTheme
import androidx.core.net.toUri

@Composable
fun MusicPlayer() {
    MusicPlayerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            AudioListScreen(modifier = Modifier.padding(innerPadding))
            AudioPlayerScreen(
                modifier = Modifier.padding(innerPadding),
                audio = Audio(
                    id = 50,
                    title = "adhuri-kahani-jo-na-hosaki-puri-hindi-song-236791",
                    artist = "<unknown>",
                    duration = 222720,
                    contentUri = "content://media/external/audio/media/50".toUri()
                )
            )
        }
    }
}