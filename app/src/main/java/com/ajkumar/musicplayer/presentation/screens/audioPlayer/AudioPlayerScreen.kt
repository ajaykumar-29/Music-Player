package com.ajkumar.musicplayer.presentation.screens.audioPlayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ajkumar.musicplayer.R
import com.ajkumar.musicplayer.domain.model.Audio
import com.ajkumar.musicplayer.presentation.theme.MusicPlayerTheme
import com.ajkumar.musicplayer.presentation.viewmodel.AudioPlayerViewModel

@Composable
fun AudioPlayerScreen(
    modifier: Modifier = Modifier,
    audio: Audio,
    audioPlayerViewModel: AudioPlayerViewModel = hiltViewModel()
) {
    val position by audioPlayerViewModel.progress.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        audioPlayerViewModel.startTrackingProgress()
    }
    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = audio.contentUri,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
        Slider(
            value = position.toFloat(),
            valueRange = 0f..audio.duration.toFloat(),
            onValueChange = {
                audioPlayerViewModel.seekTo(it.toULong().toLong())
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter), horizontalArrangement = Arrangement.SpaceAround
        ) {

            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.fast_reverse), contentDescription = "ArrowBack")
            }
            IconButton(onClick = {
                audioPlayerViewModel.playPause()
            }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "PlayArrow")
            }
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.fast_forward), contentDescription = "ArrowBack")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AudioPlayerScreenPreview() {
    MusicPlayerTheme {
    }
}
