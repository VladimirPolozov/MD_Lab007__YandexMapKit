package com.example.md_lab007__yandexmapkit

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.md_lab007__yandexmapkit.ui.screens.MapScreen
import com.example.md_lab007__yandexmapkit.ui.theme.MD_Lab007__YandexMapKitTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MD_Lab007__YandexMapKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val permissionState =
                        rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

                    when {
                        permissionState.status.isGranted -> {
                            MapScreen(modifier = Modifier.padding(innerPadding))
                        }

                        else -> {
                            LaunchedEffect(Unit) {
                                permissionState.launchPermissionRequest()
                            }
                        }
                    }
                }
            }
        }
    }
}