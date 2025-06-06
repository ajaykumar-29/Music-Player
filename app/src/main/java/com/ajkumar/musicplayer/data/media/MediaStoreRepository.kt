package com.ajkumar.musicplayer.data.media

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.ajkumar.musicplayer.domain.model.Audio
import com.ajkumar.musicplayer.domain.repository.IMediaStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import androidx.core.net.toUri

class MediaStoreRepository(private val contentResolver: ContentResolver) : IMediaStoreRepository {
    override suspend fun getAudioList(): Flow<List<Audio>> = flow {
        val audioList = withContext(Dispatchers.IO) {
            val audioList = mutableListOf<Audio>()

            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID
            )
            val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

            contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val durationIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val albumIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idIndex)
                    val title = cursor.getString(titleIndex)
                    val artist = cursor.getString(artistIndex)
                    val duration = cursor.getLong(durationIndex)
                    val contentUri = Uri.withAppendedPath(uri, id.toString())
                    val albumId = cursor.getLong(albumIdIndex)
                    val albumArtUri = getAlbumArtUri(albumId)


                    audioList.add(Audio(id, title, artist, duration, contentUri, albumArtUri))
                }
            }
            Log.d("MediaStoreRepository", "Audio list fetched: $audioList")
            audioList
        }
        emit(audioList)
    }

    private fun getAlbumArtUri(albumId: Long): Uri? {
        val albumUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        Log.d("MediaStoreRepository", "Fetching album art for albumUri: $albumUri")
        val projection = arrayOf(MediaStore.Audio.Albums.ALBUM_ART)
        val selection = "${MediaStore.Audio.Albums._ID}=?"
        val selectionArgs = arrayOf(albumId.toString())


        contentResolver.query(
            albumUri,
            projection,
            selection,
            selectionArgs,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val albumArtColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART)
                val albumArtPath = cursor.getString(albumArtColumn)
                Log.d("MediaStoreRepository", "Album art path: $albumArtColumn")
                return albumArtPath?.let { "file://$it".toUri() }
            }
        }

        return null
    }
}