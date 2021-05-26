package com.fjmartins.webuy.ui.main

import com.fjmartins.webuy.model.Listing

interface OnClickListener {
    fun onClick(index: Int, listing: Listing)
}