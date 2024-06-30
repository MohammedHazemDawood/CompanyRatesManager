package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientRate(
    val id: Int = 0,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("client_name")
    val clientName: String,
    @SerialName("rates_ids")
    val answersIds: List<Int>
){
    companion object {
        const val TABLE_NAME = "client_rate_once"
    }
}
