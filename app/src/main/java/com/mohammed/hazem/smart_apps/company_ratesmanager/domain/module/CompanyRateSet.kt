package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

sealed class CompanyRateSet {
    abstract val id: Int
    abstract val isDeleted: Boolean
    abstract val question: String

    data class TextQuestion(
        override val id: Int,
        override val isDeleted: Boolean,
        override val question: String,
    ) : CompanyRateSet()

    data class NumberQuestion(
        override val id: Int,
        override val isDeleted: Boolean,
        override val question: String,
        val start: Int,
        val end: Int
    ) : CompanyRateSet()


    data class MultiChoiceQuestion(
        override val id: Int,
        override val isDeleted: Boolean,
        override val question: String,
        val choices: List<String>
    ) : CompanyRateSet()
}