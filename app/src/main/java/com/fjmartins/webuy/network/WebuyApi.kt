package com.fjmartins.webuy.network

import retrofit2.http.GET

interface WebuyApi {
    // TODO: If the app had a proper API this is where I would set the URL and key

    companion object {
        const val KEY = "key"
        const val URL = "https://raw.githubusercontent.com/fjmartins/webuy/master/data/"
    }

    @GET("timeline.json")
    suspend fun getListings(): ListingsResponse
}