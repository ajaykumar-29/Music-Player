package com.ajkumar.musicplayer.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajkumar.musicplayer.domain.model.Audio
import com.ajkumar.musicplayer.presentation.screens.audioList.AudioListScreen
import com.ajkumar.musicplayer.presentation.screens.audioPlayer.AudioPlayerScreen

@Composable
fun MusicPlayerNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "audio_list") {
        composable("audio_list") {
            AudioListScreen(modifier = modifier)
        }
        composable("audio_player") {
            AudioPlayerScreen(
                modifier = modifier,
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