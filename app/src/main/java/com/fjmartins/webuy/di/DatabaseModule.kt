package com.fjmartins.webuy.di

import android.content.Context
import androidx.room.Room
import com.fjmartins.webuy.dao.WebuyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        WebuyDatabase::class.java,
        "WeBuy-DB"
    ).build()

    @Singleton
    @Provides
    fun provideMarketDao(database: WebuyDatabase) = database.marketDao()
}