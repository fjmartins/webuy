package com.fjmartins.webuy.dao

import androidx.room.Room
import com.fjmartins.webuy.base.BaseUnitTest
import com.fjmartins.webuy.model.Listing
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WebuyDatabaseUnitTest : BaseUnitTest() {

    private lateinit var database: WebuyDatabase

    @Before
    fun initializeDb() {
        database = Room.databaseBuilder(
            context,
            WebuyDatabase::class.java, "WeBuy-DB-test"
        ).allowMainThreadQueries() // Only for testing
            .build()

        database.clearAllTables()
    }

    @Test
    fun insertTest() = runBlocking {
        val testListing = Listing(
            1,
            "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
            "ロエベ001MAN オードゥパルファム100ml",
            7500,
            1,
            0,
            null,
            "ピロシキ",
            true,
            4,
            0
        )

        val result = database.marketDao().createListing(testListing)
        assertEquals(1, result)
    }

    @Test
    fun insertMultipleTest() = runBlocking {
        val testListings = ArrayList<Listing>().apply{
            add(Listing(
                0,
                "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
                "ロエベ001MAN オードゥパルファム100ml",
                7500,
                1,
                0,
                null,
                "ピロシキ",
                true,
                4,
                0
            ))
            add(Listing(
                0,
                "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
                "ロエベ001MAN オードゥパルファム100ml",
                7500,
                1,
                0,
                null,
                "ピロシキ",
                true,
                4,
                0
            ))
        }

        val result = database.marketDao().createListings(testListings)
        assertEquals(testListings.size, result.size)
    }

    @Test
    fun readTest() = runBlocking {
        val testListing = Listing(
            0,
            "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
            "ロエベ001MAN オードゥパルファム100ml",
            7500,
            1,
            0,
            null,
            "ピロシキ",
            true,
            4,
            0
        )
        database.marketDao().createListing(testListing)
        val result = database.marketDao().readListing(testListing.item_name).first()
        assertEquals(result.item_name, testListing.item_name)
    }

    @Test
    fun readLikeTest() = runBlocking {
        val testListing = Listing(
            0,
            "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
            "ロエベ001MAN オードゥパルファム100ml",
            7500,
            1,
            0,
            null,
            "ピロシキ",
            true,
            4,
            0
        )

        database.marketDao().createListing(testListing)

        val result = database.marketDao().readListings("%ロエベ%")
        assertEquals(true, result.isNotEmpty())
    }

    @Test
    fun updateTest() = runBlocking {
        val testListing = Listing(
            0,
            "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
            "ロエベ001MAN オードゥパルファム100ml",
            7500,
            1,
            0,
            null,
            "ピロシキ",
            true,
            4,
            0
        )

        database.marketDao().createListing(testListing)
        testListing.price = 10000

        database.marketDao().updateListing(testListing)
        val result = database.marketDao().readListing(testListing.item_name).first()
        assertEquals(result.item_name, testListing.item_name)
        assertEquals(result.price, testListing.price)
    }

    @Test
    fun deleteTest() = runBlocking {
        val testListing = Listing(
            0,
            "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
            "ロエベ001MAN オードゥパルファム100ml",
            7500,
            1,
            0,
            null,
            "ピロシキ",
            true,
            4,
            0
        )

        database.marketDao().createListing(testListing)
        val deletedRows = database.marketDao().deleteListing(testListing)
        assertEquals(1, deletedRows)
    }

    @Test
    fun deleteMultipleTest() = runBlocking {
        val testListings = ArrayList<Listing>().apply{
            add(Listing(
                0,
                "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
                "ロエベ001MAN オードゥパルファム100ml",
                7500,
                1,
                0,
                null,
                "ピロシキ",
                true,
                4,
                0
            ))
            add(Listing(
                0,
                "https://img.fril.jp/img/387745513/m/1094046576.jpg?1608014938",
                "ロエベ001MAN オードゥパルファム100ml",
                7500,
                1,
                0,
                null,
                "ピロシキ",
                true,
                4,
                0
            ))
        }

        database.marketDao().createListings(testListings)
        val result = database.marketDao().deleteAll()
        val successCode = 1
        assertEquals(successCode, result)
    }

    @After
    fun finalize() {
        database.clearAllTables()
        database.close()
    }
}