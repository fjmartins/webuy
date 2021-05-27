package com.fjmartins.webuy.network

import com.fjmartins.webuy.model.Listing

data class ListingsResponse(
    val items: List<Listing>?
)