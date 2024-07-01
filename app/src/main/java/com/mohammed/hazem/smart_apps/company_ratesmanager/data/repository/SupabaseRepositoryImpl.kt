package com.mohammed.hazem.smart_apps.company_ratesmanager.data.repository

import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientAnswer
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientRate
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientRateSupabseTable
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.CompanyRateSet
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class SupabaseRepositoryImpl @Inject constructor(private val supabaseClient: SupabaseClient) :
    SupabaseRepository {
    override suspend fun getCompanyRatesSets(): List<CompanyRateSet> =
        supabaseClient.from(CompanyRateSet.TABLE_NAME).select().decodeList()

    override suspend fun getClientsAnswers(): List<ClientAnswer> =
        supabaseClient.from(ClientAnswer.TABLE_NAME).select().decodeList()

    override suspend fun getClientsRates(): List<ClientRate> =
        supabaseClient.from(ClientRateSupabseTable.TABLE_NAME).select()
            .decodeList<ClientRateSupabseTable>().map {
                ClientRate(
                    it.id,
                    it.createdAt,
                    it.clientName,
                    it.answersIds.map { answerId -> getClientAnswerById(answerId) })
            }//end of map

    //get by id
    override suspend fun getCompanyRateSetById(id: Int): CompanyRateSet =
        supabaseClient.from(CompanyRateSet.TABLE_NAME).select { filter { eq("id", id) } }.decodeAs()

    override suspend fun getClientAnswerById(id: Int): ClientAnswer =
        supabaseClient.from(ClientAnswer.TABLE_NAME)
            .select { filter { eq("id", id) } }.decodeAs()

    override suspend fun getClientRateById(id: Int): ClientRate =
        supabaseClient.from(ClientRateSupabseTable.TABLE_NAME)
            .select { filter { eq("id", id) } }.decodeAs<ClientRateSupabseTable>().let {
                ClientRate(
                    it.id,
                    it.createdAt,
                    it.clientName,
                    it.answersIds.map { answerId -> getClientAnswerById(answerId) })
            }//end of let

    //upsert -> insert or update
    override suspend fun upsertCompanyRateSet(companyRateSet: CompanyRateSet) {
        supabaseClient.from(CompanyRateSet.TABLE_NAME).upsert(companyRateSet)
    }//end of upsertCompanyRateSet

    override suspend fun upsertClientAnswer(clientAnswer: ClientAnswer) {
        supabaseClient.from(ClientAnswer.TABLE_NAME).upsert(clientAnswer)
    }//end of upsertClientAnswer

    override suspend fun upsertClientRate(clientRate: ClientRate) {
        supabaseClient.from(ClientRateSupabseTable.TABLE_NAME).upsert(clientRate)
    }//end of upsertClientRate

    //delete
    override suspend fun deleteCompanyRateSet(companyRateSet: CompanyRateSet) {
        supabaseClient.from(CompanyRateSet.TABLE_NAME)
            .delete { filter { eq("id", companyRateSet.id) } }
    }//end of deleteCompanyRateSet

    override suspend fun deleteClientAnswer(clientAnswer: ClientAnswer) {
        supabaseClient.from(ClientAnswer.TABLE_NAME).delete { filter { eq("id", clientAnswer.id) } }
    }//end of deleteClientAnswer

    override suspend fun deleteClientRate(clientRate: ClientRate) {
        supabaseClient.from(ClientRateSupabseTable.TABLE_NAME)
            .delete { filter { eq("id", clientRate.id) } }
    }//end of deleteClientRate
}//end of SupabaseRepositoryImpl