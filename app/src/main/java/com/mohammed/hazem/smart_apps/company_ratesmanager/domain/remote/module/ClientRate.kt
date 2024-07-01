package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module

data class ClientRate(
    val id: Int = 0,
    val createdAt: String,
    val clientName: String,
    val answersIds: List<ClientAnswer>
)