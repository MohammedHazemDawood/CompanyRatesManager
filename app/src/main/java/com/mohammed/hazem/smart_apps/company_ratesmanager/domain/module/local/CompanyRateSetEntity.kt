package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.CompanyRateSet

@Entity(tableName = CompanyRateSet.TABLE_NAME)
data class CompanyRateSetEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val question: String,
    val type: String,
    val isDeleted: Boolean = false,
    val start: Int?,
    val end: Int?,
    val choices: List<String>?
)