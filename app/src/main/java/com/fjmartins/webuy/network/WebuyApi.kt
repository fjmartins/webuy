package com.fjmartins.webuy.network

import retrofit2.http.GET

interface WebuyApi {
    // TODO: Read key from local.properties
    companion object {
        const val KEY = "" // BuildConfig.WEBUY_API_KEY
        const val URL = "https://raw.githubusercontent.com/fjmartins/webuy/master/data/"
    }

    @GET("timeline.json")
    suspend fun getListings(): ListingsResponse
}