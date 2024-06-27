package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

import kotlinx.serialization.SerialName

sealed class ClientAnswer {
    abstract val id: Int

    @SerialName("created_at")
    abstract val createdAt: String

    @SerialName("question_id")
    abstract val questionId: Int

    data class TextAnswer(
        override val id: Int,
        override val createdAt: String,
        override val questionId: Int,
        @SerialName("text_feedback")
        val answer: String
    ) : ClientAnswer()

    data class NumberAnswer(
        override val id: Int,
        override val createdAt: String,
        override val questionId: Int,
        @SerialName("range_value")
        val answer: Int
    ) : ClientAnswer()

    data class MultiChoiceAnswer(
        override val id: Int,
        override val createdAt: String,
        override val questionId: Int,
        @SerialName("choice")
        val answer: String
    ) : ClientAnswer()
}