package com.example.md_lab007__yandexmapkit.data.local


data class OsrmResponse(
    val code: String,
    val routes: List<OsrmRoute>,
    val waypoints: List<OsrmWaypoint>
)

data class OsrmRoute(
    val distance: Double,
    val duration: Double,
    val geometry: String,
    val legs: List<OsrmLeg>
)

data class OsrmLeg(
    val distance: Double,
    val duration: Double,
    val steps: List<OsrmStep>
)

data class OsrmStep(
    val distance: Double,
    val duration: Double,
    val geometry: String,
    val name: String,
    val mode: String,
    val maneuver: OsrmManeuver
)

data class OsrmManeuver(
    val location: List<Double>,
    val type: String,
    val modifier: String?
)

data class OsrmWaypoint(
    val name: String,
    val location: List<Double>,
    val distance: Double
)