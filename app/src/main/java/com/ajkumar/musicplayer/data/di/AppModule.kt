package com.ajkumar.musicplayer.data.di

import android.content.Context
import com.ajkumar.musicplayer.data.media.MediaStoreRepository
import com.ajkumar.musicplayer.domain.repository.IMediaStoreRepository
import com.ajkumar.musicplayer.domain.usecase.GetAudioListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMediaStoreRepository(@ApplicationContext context: Context): IMediaStoreRepository {
        return MediaStoreRepository(context.contentResolver)
    }

    @Provides
    @Singleton
    fun provideGetAudioListUseCase(repository: IMediaStoreRepository): GetAudioListUseCase {
        return GetAudioListUseCase(repository)
    }
}