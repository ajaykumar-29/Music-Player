package com.ajkumar.musicplayer.domain.usecase

import com.ajkumar.musicplayer.domain.model.Audio
import com.ajkumar.musicplayer.domain.repository.IMediaStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAudioListUseCase @Inject constructor(private val repository: IMediaStoreRepository) {
    suspend operator fun invoke(): Flow<List<Audio>> = repository.getAudioList()
}