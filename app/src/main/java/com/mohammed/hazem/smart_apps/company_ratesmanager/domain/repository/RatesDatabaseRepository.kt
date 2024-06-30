package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.repository

import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.ClientAnswerEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.ClientRateEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.CompanyRateSetEntity
import kotlinx.coroutines.flow.Flow

interface RatesDatabaseRepository{
    // get all
    val companyRateSets : Flow<List<CompanyRateSetEntity>>
    val clientAnswers : Flow<List<ClientAnswerEntity>>
    val clientRates : Flow<List<ClientRateEntity>>

    // get by id
    fun getCompanyRateSetById(id : Int) : Flow<CompanyRateSetEntity>
    fun getClientAnswerById(id : Int) : Flow<ClientAnswerEntity>
    fun getClientRateById(id : Int) : Flow<ClientRateEntity>

    // upsert -> insert or update
    suspend fun upsertCompanyRateSet(companyRateSetEntity: CompanyRateSetEntity)
    suspend fun upsertClientAnswer(clientAnswerEntity: ClientAnswerEntity)
    suspend fun upsertClientRate(clientRateEntity: ClientRateEntity)

    // delete
    suspend fun deleteCompanyRateSet(companyRateSetEntity: CompanyRateSetEntity)
    suspend fun deleteClientAnswer(clientAnswerEntity: ClientAnswerEntity)
    suspend fun deleteClientRate(clientRateEntity: ClientRateEntity)
}