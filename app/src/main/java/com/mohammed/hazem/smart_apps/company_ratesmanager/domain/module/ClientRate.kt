package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

import kotlinx.serialization.SerialName

data class ClientRate(
    val id: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("client_name")
    val clientName: String,
    @SerialName("company_ids")
    val companyId: List<Int>
)
