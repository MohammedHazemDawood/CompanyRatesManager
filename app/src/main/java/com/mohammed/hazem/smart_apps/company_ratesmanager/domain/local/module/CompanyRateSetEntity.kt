package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.local.module

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.CompanyRateSet

@Entity(tableName = CompanyRateSet.TABLE_NAME)
data class CompanyRateSetEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val question: String,
    val type: String,
    val start: Int?,
    val end: Int?,
    val choices: List<String>?
)