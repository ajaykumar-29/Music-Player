package com.ajkumar.musicplayer.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajkumar.musicplayer.domain.model.Audio
import com.ajkumar.musicplayer.domain.usecase.GetAudioListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioListViewModel @Inject constructor(
    private val getAudioListUseCase: GetAudioListUseCase,
) : ViewModel() {

    private val _audioList = MutableStateFlow<List<Audio>>(emptyList())
    val audioList: StateFlow<List<Audio>> = _audioList


    init {
        fetchAudioList()
    }

    private fun fetchAudioList() {
        viewModelScope.launch(Dispatchers.IO) {
            getAudioListUseCase().collectLatest { audioList ->
                _audioList.value = audioList
            }
            Log.d("AudioListViewModel", "Audio list fetched: ${audioList.value}")
        }
    }
}