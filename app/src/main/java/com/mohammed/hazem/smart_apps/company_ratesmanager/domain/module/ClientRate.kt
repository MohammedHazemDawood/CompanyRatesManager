package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ClientRate(
    val id: Int = 0,
    val createdAt: String,
    val clientName: String,
    val answersIds: List<ClientAnswer>
)