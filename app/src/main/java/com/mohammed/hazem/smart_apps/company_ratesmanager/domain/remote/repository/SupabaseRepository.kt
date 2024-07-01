package com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.repository

import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientAnswer
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientRate
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.CompanyRateSet

interface SupabaseRepository {

    // get all
    suspend fun getCompanyRatesSets(): List<CompanyRateSet>

    suspend fun getClientsAnswers(): List<ClientAnswer>

    suspend fun getClientsRates(): List<ClientRate>

    // get by id
    suspend fun getCompanyRateSetById(id: Int): CompanyRateSet

    suspend fun getClientAnswerById(id: Int): ClientAnswer

    suspend fun getClientRateById(id: Int): ClientRate


    // upsert -> insert or update
    suspend fun upsertCompanyRateSet(companyRateSet: CompanyRateSet)

    suspend fun upsertClientAnswer(clientAnswer: ClientAnswer)

    suspend fun upsertClientRate(clientRate: ClientRate)


    // delete
    suspend fun deleteCompanyRateSet(companyRateSet: CompanyRateSet)

    suspend fun deleteClientAnswer(clientAnswer: ClientAnswer)

    suspend fun deleteClientRate(clientRate: ClientRate)

}//end of SupabaseRepository