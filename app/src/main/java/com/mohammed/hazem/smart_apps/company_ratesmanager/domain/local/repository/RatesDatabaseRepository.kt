package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.local.repository

import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientAnswer
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientRate
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.CompanyRateSet
import kotlinx.coroutines.flow.Flow

interface RatesDatabaseRepository{
    // get all
    val companyRateSets : Flow<List<CompanyRateSet>>
    val clientAnswers : Flow<List<ClientAnswer>>
    val clientRates : Flow<List<ClientRate>>

    // get by id
    fun getCompanyRateSetById(id : Int) : Flow<CompanyRateSet>
    fun getClientAnswerById(id : Int) : Flow<ClientAnswer>
    fun getClientRateById(id : Int) : Flow<ClientRate>

    // upsert -> insert or update
    suspend fun upsertCompanyRateSet(companyRateSet: CompanyRateSet)
    suspend fun upsertClientAnswer(clientAnswer: ClientAnswer)
    suspend fun upsertClientRate(clientRate: ClientRate)

    // delete
    suspend fun deleteCompanyRateSet(companyRateSet: CompanyRateSet)
    suspend fun deleteClientAnswer(clientAnswer: ClientAnswer)
    suspend fun deleteClientRate(clientRate: ClientRate)
}