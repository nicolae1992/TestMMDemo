package com.appenginex.com.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appenginex.com.database.dao.CreditCardDao
import com.appenginex.com.database.entities.CreditCardEntity

@Database(
    entities = [CreditCardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun creditCardsDao(): CreditCardDao
}