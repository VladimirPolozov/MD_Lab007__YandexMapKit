package com.example.md_lab007__yandexmapkit.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.md_lab007__yandexmapkit.R
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    vm: MapViewModel = viewModel<MapViewModel>(),
    modifier: Modifier
) {
    val cameraPositionState = rememberCameraPositionState()

    Box(modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true
            )
        )
        Button(
            onClick = { vm.moveToPredefinedLocation(cameraPositionState) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(stringResource(R.string.ScalingButtonText))
        }
    }
}