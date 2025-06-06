package com.ajkumar.musicplayer.domain.repository

import com.ajkumar.musicplayer.domain.model.Audio
import kotlinx.coroutines.flow.Flow

interface IMediaStoreRepository {
    suspend fun getAudioList(): Flow<List<Audio>>
}