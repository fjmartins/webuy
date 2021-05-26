package com.fjmartins.webuy.repository

import com.fjmartins.webuy.dao.WebuyDatabase
import com.fjmartins.webuy.model.Listing
import com.fjmartins.webuy.network.WebuyApi
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class WebuyRepository @Inject constructor(
    private val webuyDatabase: WebuyDatabase,
    private val webuyApi: WebuyApi
) {
    suspend fun getListings(): List<Listing>? {

        return try {
            val response = webuyApi.getListings()

            webuyDatabase
                .marketDao()
                .createListings(response.items.orEmpty())

            response.items
        } catch (e: UnknownHostException) { // If Offline Load from cache
            webuyDatabase
                .marketDao()
                .readListings()
        } catch (e: Exception) {
            null
        }
    }
}