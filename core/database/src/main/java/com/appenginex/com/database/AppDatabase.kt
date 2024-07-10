package com.appenginex.com.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appenginex.com.database.dao.CreditCardDao
import com.appenginex.com.database.entities.CreditCardEntity


const val DATABASE_NAME = "app_engine_x_demo_db"
@Database(
    entities = [CreditCardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun creditCardsDao(): CreditCardDao
}