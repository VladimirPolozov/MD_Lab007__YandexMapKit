package com.example.md_lab007__yandexmapkit.data.api

import com.example.md_lab007__yandexmapkit.data.local.OsrmResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://router.project-osrm.org"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface OsrmApiService {
    @GET("route/v1/driving/{coordinates}")
    suspend fun getRoute(
        @Path("coordinates") coordinates: String,
        @Query("overview") overview: String = "full",
        @Query("geometries") geometries: String = "polyline"
    ): OsrmResponse
}

class OsrmApi {
    companion object {
        fun create(): OsrmApiService {
            val retrofitService: OsrmApiService by lazy {
                retrofit.create(OsrmApiService::class.java)
            }
            return retrofitService
        }
    }
}