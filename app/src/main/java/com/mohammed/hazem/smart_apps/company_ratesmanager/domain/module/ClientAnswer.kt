package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ClientAnswer {
    abstract val id: Int

    @SerialName("created_at")
    abstract val createdAt: String

    @SerialName("question_id")
    abstract val questionId: Int?

    abstract val type: String

    @Serializable
    data class TextAnswer(
        override val id: Int = 0,
        override val createdAt: String,
        override val questionId: Int?,
        @SerialName("text_feedback")
        val answer: String,
        override val type: String
    ) : ClientAnswer()

    @Serializable
    data class NumberAnswer(
        override val id: Int = 0,
        override val createdAt: String,
        override val questionId: Int?,
        @SerialName("range_value")
        val answer: Int,
        override val type: String
    ) : ClientAnswer()

    @Serializable
    data class MultiChoiceAnswer(
        override val id: Int = 0,
        override val createdAt: String,
        override val questionId: Int?,
        @SerialName("choice")
        val answer: String,
        override val type: String
    ) : ClientAnswer()

    companion object {
        const val TABLE_NAME = "client_answer"
    }
}