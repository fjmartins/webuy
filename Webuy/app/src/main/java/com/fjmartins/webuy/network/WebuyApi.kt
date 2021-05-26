package com.fjmartins.webuy.network

import retrofit2.http.GET

interface WebuyApi {
    companion object {
        const val KEY = "key"
        const val URL = "https://raw.githubusercontent.com/https://github.com/fjmartins/webuy/master/data/"
    }

    @GET("timeline.json")
    suspend fun getListings(): ListingsResponse
}