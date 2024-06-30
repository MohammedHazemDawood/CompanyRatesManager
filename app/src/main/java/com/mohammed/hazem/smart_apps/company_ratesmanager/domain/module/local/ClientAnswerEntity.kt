package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.ClientAnswer

@Entity(
    tableName = ClientAnswer.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = CompanyRateSetEntity::class,
        parentColumns = ["id"],
        childColumns = ["question_id"]
    )]
)
data class ClientAnswerEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    val type: String,
    val answer: String?,
    val rangeValue: Int?,
    val choice: String?
)
