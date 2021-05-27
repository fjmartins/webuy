package com.fjmartins.webuy.api

import com.fjmartins.webuy.base.BaseUnitTest
import com.fjmartins.webuy.network.WebuyApi
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class WebuyApiUnitTest : BaseUnitTest() {

    lateinit var webuyApi: WebuyApi

    @Before
    fun setUp() {
        System.setProperty("javax.net.ssl.trustStoreType", "JKS")

        webuyApi = Retrofit.Builder()
            .baseUrl(WebuyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS).connectTimeout(15, TimeUnit.SECONDS).build())
            .build()
            .create(WebuyApi::class.java)
    }

    @Test
    fun getMarketListings() = runBlocking {
        // TODO: Note; There is no fail/success response code so for now I am just checking if "items" in the response json is empty or not

        val response = webuyApi.getListings()
        assertEquals(true, response.items != null)
    }
}