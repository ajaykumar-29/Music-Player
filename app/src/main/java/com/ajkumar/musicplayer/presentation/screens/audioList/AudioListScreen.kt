package com.ajkumar.musicplayer.presentation.screens.audioList

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ajkumar.musicplayer.presentation.viewmodel.AudioListViewModel
import com.ajkumar.musicplayer.presentation.viewmodel.AudioPlayerViewModel

@Composable
fun AudioListScreen(
    modifier: Modifier = Modifier,
    audioListViewModel: AudioListViewModel = hiltViewModel(),
    audioPlayerViewModel: AudioPlayerViewModel = hiltViewModel()
) {
    val audioList by audioListViewModel.audioList.collectAsState()

    LaunchedEffect(audioList) {
        Log.d("MusicPlayer", "Audio list fetched: $audioList")
    }

    LazyColumn(modifier = modifier) {
        items(audioList) { audio ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { audioPlayerViewModel.setAudio(audio.contentUri) }
                    .padding(16.dp)
            ) {
                Text(text = audio.title)
            }
        }
    }
}
