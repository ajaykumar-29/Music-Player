package com.ajkumar.musicplayer.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionManager {

    fun getRequiredAudioPermission(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    }

    fun isPermissionGranted(context: Context): Boolean {
        val permission = getRequiredAudioPermission()
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissionIfNeeded(
        context: Context,
        launcher: ActivityResultLauncher<String>,
        onPermissionGranted: () -> Unit
    ) {
        val permission = getRequiredAudioPermission()
        if (isPermissionGranted(context)) {
            onPermissionGranted()
        } else {
            launcher.launch(permission)
        }
    }
}