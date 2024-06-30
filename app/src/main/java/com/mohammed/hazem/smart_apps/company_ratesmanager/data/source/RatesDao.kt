package com.mohammed.hazem.smart_apps.company_ratesmanager.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.ClientAnswerEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.ClientRateEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.module.local.CompanyRateSetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RatesDao {

    //get all
    @Query("SELECT * FROM co_rate_set")
    fun getAllCompanyRateSets(): Flow<List<CompanyRateSetEntity>>

    @Query("SELECT * FROM client_answer")
    fun getAllClientRatesOnce(): Flow<List<ClientAnswerEntity>>

    @Query("SELECT * FROM client_rate")
    fun getAllClientRates(): Flow<List<ClientRateEntity>>

    //get by id
    @Query("SELECT * FROM co_rate_set WHERE id = :id")
    fun getCompanyRateSet(id: Int): Flow<CompanyRateSetEntity>

    @Query("SELECT * FROM client_answer WHERE id = :id")
    fun getClientAnswer(id: Int): Flow<ClientAnswerEntity>

    @Query("SELECT * FROM client_rate WHERE id = :id")
    fun getClientRate(id: Int): Flow<ClientRateEntity>


    //upsert -> insert or update
    @Upsert
    suspend fun upsertCompanyRateSet(companyRateSet: CompanyRateSetEntity)

    @Upsert
    suspend fun upsertClientAnswer(clientAnswer: ClientAnswerEntity)

    @Upsert
    suspend fun upsertClientRate(clientRate: ClientRateEntity)

    //delete
    @Delete
    suspend fun deleteCompanyRateSet(companyRateSet: CompanyRateSetEntity)

    @Delete
    suspend fun deleteClientAnswer(clientAnswer: ClientAnswerEntity)

    @Delete
    suspend fun deleteClientRate(clientRate: ClientRateEntity)

}//end of RatesDao