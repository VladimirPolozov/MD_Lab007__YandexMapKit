package com.example.md_lab007__yandexmapkit.ui.screens


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.md_lab007__yandexmapkit.data.api.OsrmApi
import com.example.md_lab007__yandexmapkit.data.api.OsrmApiService
import com.example.md_lab007__yandexmapkit.data.local.Points
import com.example.md_lab007__yandexmapkit.data.local.Route
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.internal.PolylineEncoding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel() : ViewModel() {
    private val pochtamtCords = LatLng(55.354993, 86.085805)

    private val _points = mutableStateOf(Points())
    val points: State<Points> = _points

    private val _route = mutableStateOf(Route())
    val route: State<Route> = _route

    fun moveToPredefinedLocation(cameraPositionState: CameraPositionState) {
        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(pochtamtCords, 15f))
    }

    fun addPoint(point: LatLng) {
        val current = _points.value
        if (current.start == null) {
            _points.value = current.copy(start = point)
        } else if (current.end == null) {
            _points.value = current.copy(end = point)
        } else {
            _points.value = Points()
            _route.value = Route()
        }
    }

    fun buildRoute() {
        val api: OsrmApiService = OsrmApi.create()
        val coordinates =
            "${_points.value.start?.longitude},${_points.value.start?.latitude};" +
                    "${_points.value.end?.longitude},${_points.value.end?.latitude}"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getRoute(coordinates)
                _route.value = Route(
                    points = decodePolyline(response.routes[0].geometry),
                    polyline = response.routes[0].geometry
                )
            } catch (_: Exception) {
            }
        }
    }

    private fun decodePolyline(encoded: String): List<LatLng> {
        return try {
            PolylineEncoding.decode(encoded).map {
                LatLng(it.lat, it.lng)
            }
        } catch (_: Exception) {
            emptyList()
        }
    }
}