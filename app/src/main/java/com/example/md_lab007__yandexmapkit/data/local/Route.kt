package com.example.md_lab007__yandexmapkit.data.local

import com.google.android.gms.maps.model.LatLng

data class Route(
    val points: List<LatLng> = emptyList(),
    val polyline: String = ""
)