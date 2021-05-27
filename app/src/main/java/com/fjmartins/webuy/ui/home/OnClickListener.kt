package com.fjmartins.webuy.ui.home

import com.fjmartins.webuy.model.Listing

interface OnClickListener {
    fun onClick(index: Int, listing: Listing)
}