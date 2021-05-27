package com.fjmartins.webuy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listings")
data class Listing (
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    var item_id: Int,
    @ColumnInfo(name = "img_url")
    var img_url: String,
    @ColumnInfo(name = "item_name")
    var item_name: String,
    @ColumnInfo(name = "price") // In JPY
    var price: Int,
    @ColumnInfo(name = "brand_id")
    var brand_id: Int,
    @ColumnInfo(name = "t_status")
    var t_status: Int,
    @ColumnInfo(name = "brand_name")
    var brand_name: String?,
    @ColumnInfo(name = "screen_name")
    var screen_name: String,
    @ColumnInfo(name = "liked")
    var liked: Boolean,
    @ColumnInfo(name = "like_count")
    var like_count: Int,
    @ColumnInfo(name = "comment_count")
    var comment_count: Int
)