package com.fjmartins.webuy.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fjmartins.webuy.model.Listing

@Database(entities = [Listing::class], version = 1)
abstract class WebuyDatabase : RoomDatabase(){
    abstract fun marketDao() : MarketDao
}