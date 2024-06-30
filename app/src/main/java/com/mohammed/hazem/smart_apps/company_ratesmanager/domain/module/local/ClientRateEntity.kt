package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.ClientRateSupabseTable

@Entity(tableName = ClientRateSupabseTable.TABLE_NAME)
data class ClientRateEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "client_name")
    val clientName: String,
    @ColumnInfo(name = "rates_ids")
    val answersIds: List<Int>
)