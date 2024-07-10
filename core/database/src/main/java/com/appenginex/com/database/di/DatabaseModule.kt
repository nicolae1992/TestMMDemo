package com.appenginex.com.database.di

import android.app.Application
import androidx.room.Room
import com.appenginex.com.database.AppDatabase
import com.appenginex.com.database.DATABASE_NAME
import com.appenginex.com.database.dao.CreditCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): CreditCardDao {
        return appDatabase.creditCardsDao()
    }
}