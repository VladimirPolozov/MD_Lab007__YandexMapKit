package com.example.md_lab007__yandexmapkit.ui.screens

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class MapViewModel : ViewModel() {
    private val pochtamtCords = LatLng(55.354993, 86.085805)

    fun moveToPredefinedLocation(cameraPositionState: CameraPositionState) {
        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(pochtamtCords, 15f))
    }
}