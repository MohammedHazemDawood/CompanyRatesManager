package com.mohammed.hazem.smart_apps.company_ratesmanager.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.ClientAnswerEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.ClientRateEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.CompanyRateSetEntity

@Database(
    entities = [CompanyRateSetEntity::class, ClientAnswerEntity::class, ClientRateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RatesDatabase : RoomDatabase() {
    abstract fun ratesDao(): RatesDao

    companion object {
        @Volatile
        var ratesDatabase: RatesDatabase? = null

        private const val DATABASE_NAME = "rates_database"

        fun getRatesDatabase(context: Context): RatesDatabase =
            ratesDatabase ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    RatesDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
            }//end of synchronized
    }//end of companion object
}//end of RatesDatabase