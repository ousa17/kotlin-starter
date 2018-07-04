package com.example.user.kotlinbasic.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.user.kotlinbasic.data.local.dao.RoomCurrencyDao
import com.example.user.kotlinbasic.data.local.entities.CurrencyEntity

@Database(
        entities = arrayOf(CurrencyEntity::class),
        version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): RoomCurrencyDao

}