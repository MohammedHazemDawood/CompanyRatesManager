package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.jvmName

@Serializable
sealed class CompanyRateSet {
    abstract val id: Int

    @SerialName("is_deleted")
    abstract val isDeleted: Boolean

    abstract val question: String

    abstract val type: String

    @Serializable
    data class TextQuestion(
        override val id: Int = 0,
        @SerialName("is_deleted")
        override val isDeleted: Boolean = false,
        override val question: String,
        override val type: String
    ) : CompanyRateSet()

    @Serializable
    data class NumberQuestion(
        override val id: Int = 0,
        @SerialName("is_deleted")
        override val isDeleted: Boolean = false,
        override val question: String,
        val start: Int,
        val end: Int,
        override val type: String
    ) : CompanyRateSet()

    @Serializable
    data class MultiChoiceQuestion(
        override val id: Int = 0,
        @SerialName("is_deleted")
        override val isDeleted: Boolean = false,
        override val question: String,
        val choices: List<String>,
        override val type: String
    ) : CompanyRateSet()

    companion object {
        const val TABLE_NAME = "co_rate_set"
    }
}