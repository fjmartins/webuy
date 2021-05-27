package com.fjmartins.webuy.dao

import androidx.room.*
import com.fjmartins.webuy.model.Listing

@Dao
interface MarketDao {
    @Insert
    suspend fun createListing(listing: Listing): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createListings(listings: List<Listing>): List<Long>

    @Query("SELECT * FROM listings")
    suspend fun readListings(): List<Listing>

    @Query("SELECT * FROM listings WHERE item_name = :name")
    suspend fun readListing(name: String): List<Listing>

    @Query("SELECT * FROM listings WHERE item_name LIKE :like") // Enclose like with % %
    suspend fun readListings(like: String): List<Listing>

    @Update
    suspend fun updateListing(listing: Listing): Int

    @Delete
    suspend fun deleteListing(listing: Listing): Int

    @Query("DELETE FROM listings")
    suspend fun deleteAll(): Int
}