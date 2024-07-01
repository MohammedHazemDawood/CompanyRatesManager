package com.mohammed.hazem.smart_apps.company_ratesmanager.data.repository

import com.mohammed.hazem.smart_apps.company_ratesmanager.data.source.RatesDao
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.local.module.ClientAnswerEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.local.module.ClientRateEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.local.module.CompanyRateSetEntity
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.local.repository.RatesDatabaseRepository
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientAnswer
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.ClientRate
import com.mohammed.hazem.smart_apps.company_ratesmanager.domain.remote.module.CompanyRateSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RatesDatabaseRepositoryImpl @Inject constructor(private val ratesDao: RatesDao) :
    RatesDatabaseRepository {
    // get all
    override val companyRateSets: Flow<List<CompanyRateSet>>
        get() = ratesDao.getAllCompanyRateSets().map { companyRateSets ->
            companyRateSets.map { companyRateSet ->
                when (companyRateSet.type) {
                    CompanyRateSet.TextQuestion::class.java.canonicalName ->
                        CompanyRateSet.TextQuestion(
                            companyRateSet.id,
                            companyRateSet.question,
                            type = companyRateSet.type,
                        )//end of text question

                    CompanyRateSet.NumberQuestion::class.java.canonicalName ->
                        CompanyRateSet.NumberQuestion(
                            companyRateSet.id,
                            companyRateSet.question,
                            type = companyRateSet.type,
                            start = companyRateSet.start!!,
                            end = companyRateSet.end!!
                        )//end of number question

                    CompanyRateSet.MultiChoiceQuestion::class.java.canonicalName ->
                        CompanyRateSet.MultiChoiceQuestion(
                            companyRateSet.id,
                            companyRateSet.question,
                            type = companyRateSet.type,
                            choices = companyRateSet.choices!!
                        )//end of multi choice question
                    else -> throw IllegalArgumentException("Unknown question type")
                }//end of when
            }//end of list map
        }//end of flow map
    override val clientAnswers: Flow<List<ClientAnswer>>
        get() = ratesDao.getAllClientAnswers().map { clientAnswers ->
            clientAnswers.map {
                when (it.type) {
                    ClientAnswer.TextAnswer::class.java.canonicalName ->
                        ClientAnswer.TextAnswer(
                            it.id,
                            it.createdAt,
                            it.questionId,
                            it.answer!!,
                            it.type
                        )//end of text answer
                    ClientAnswer.NumberAnswer::class.java.canonicalName ->
                        ClientAnswer.NumberAnswer(
                            it.id,
                            it.createdAt,
                            it.questionId,
                            it.rangeValue!!,
                            it.type
                        )//end of number answer
                    ClientAnswer.MultiChoiceAnswer::class.java.canonicalName ->
                        ClientAnswer.MultiChoiceAnswer(
                            it.id,
                            it.createdAt,
                            it.questionId,
                            it.choice!!,
                            it.type
                        )//end of multi choice answer
                    else -> throw IllegalArgumentException("Unknown answer type")
                }//end of when
            }//end of list map
        }//end of flow map
    override val clientRates: Flow<List<ClientRate>>
        get() = ratesDao.getAllClientRates().map { clientRates ->
            clientRates.map {
                ClientRate(
                    it.id,
                    it.createdAt,
                    it.clientName,
                    it.answersIds.map { answerId -> getClientAnswerById(answerId).first() }
                )//end of client rate
            }//end of list map
        }//end of flow map


    // get by id
    override fun getCompanyRateSetById(id: Int): Flow<CompanyRateSet> =
        ratesDao.getCompanyRateSet(id).map { companyRateSet ->
            when (companyRateSet.type) {
                CompanyRateSet.TextQuestion::class.java.canonicalName ->
                    CompanyRateSet.TextQuestion(
                        companyRateSet.id,
                        companyRateSet.question,
                        companyRateSet.type,
                    )//end of text question

                CompanyRateSet.NumberQuestion::class.java.canonicalName ->
                    CompanyRateSet.NumberQuestion(
                        id = companyRateSet.id,
                        question = companyRateSet.question,
                        type = companyRateSet.type,
                        start = companyRateSet.start!!,
                        end = companyRateSet.end!!
                    )//end of number question

                CompanyRateSet.MultiChoiceQuestion::class.java.canonicalName ->
                    CompanyRateSet.MultiChoiceQuestion(
                        id = companyRateSet.id,
                        question = companyRateSet.question,
                        type = companyRateSet.type,
                        choices = companyRateSet.choices!!
                    )//end of multi choice question

                else -> throw IllegalArgumentException("Unknown question type")
            }
        }

    override fun getClientAnswerById(id: Int): Flow<ClientAnswer> =
        ratesDao.getClientAnswer(id).map { clientAnswer ->
            when (clientAnswer.type) {
                ClientAnswer.TextAnswer::class.java.canonicalName ->
                    ClientAnswer.TextAnswer(
                        clientAnswer.id,
                        clientAnswer.createdAt,
                        clientAnswer.questionId,
                        clientAnswer.answer!!,
                        clientAnswer.type
                    )

                ClientAnswer.NumberAnswer::class.java.canonicalName ->
                    ClientAnswer.NumberAnswer(
                        clientAnswer.id,
                        clientAnswer.createdAt,
                        clientAnswer.questionId,
                        clientAnswer.rangeValue!!,
                        clientAnswer.type
                    )

                ClientAnswer.MultiChoiceAnswer::class.java.canonicalName ->
                    ClientAnswer.MultiChoiceAnswer(
                        clientAnswer.id,
                        clientAnswer.createdAt,
                        clientAnswer.questionId,
                        clientAnswer.choice!!,
                        clientAnswer.type
                    )

                else -> throw IllegalArgumentException("Unknown answer type")
            }
        }

    override fun getClientRateById(id: Int): Flow<ClientRate> =
        ratesDao.getClientRate(id).map { clientRate ->
            ClientRate(
                clientRate.id,
                clientRate.createdAt,
                clientRate.clientName,
                clientRate.answersIds.map { answerId -> getClientAnswerById(answerId).first() })
        }


    // upsert -> insert or update
    override suspend fun upsertCompanyRateSet(companyRateSet: CompanyRateSet) =
        ratesDao.upsertCompanyRateSet(
            CompanyRateSetEntity(
                companyRateSet.id,
                companyRateSet.question,
                companyRateSet.type,
                (companyRateSet as? CompanyRateSet.NumberQuestion)?.start,
                (companyRateSet as? CompanyRateSet.NumberQuestion)?.end,
                (companyRateSet as? CompanyRateSet.MultiChoiceQuestion)?.choices
            )
        )

    override suspend fun upsertClientAnswer(clientAnswer: ClientAnswer) =
        ratesDao.upsertClientAnswer(
            ClientAnswerEntity(
                clientAnswer.id,
                clientAnswer.createdAt,
                clientAnswer.questionId,
                clientAnswer.type,
                (clientAnswer as? ClientAnswer.TextAnswer)?.answer,
                (clientAnswer as? ClientAnswer.NumberAnswer)?.answer,
                (clientAnswer as? ClientAnswer.MultiChoiceAnswer)?.answer
            )//end of client answer
        )//end of upsert client answer

    override suspend fun upsertClientRate(clientRate: ClientRate) =
        ratesDao.upsertClientRate(
            ClientRateEntity(
                clientRate.id,
                clientRate.createdAt,
                clientRate.clientName,
                clientRate.answersIds.map { it.id })
        )//end of upsert client rate


    // delete
    override suspend fun deleteCompanyRateSet(companyRateSet: CompanyRateSet) =
        ratesDao.deleteCompanyRateSet(
            CompanyRateSetEntity(
                companyRateSet.id,
                companyRateSet.question,
                companyRateSet.type,
                (companyRateSet as? CompanyRateSet.NumberQuestion)?.start,
                (companyRateSet as? CompanyRateSet.NumberQuestion)?.end,
                (companyRateSet as? CompanyRateSet.MultiChoiceQuestion)?.choices
            )
        )

    override suspend fun deleteClientAnswer(clientAnswer: ClientAnswer) =
        ratesDao.deleteClientAnswer(
            ClientAnswerEntity(
                clientAnswer.id,
                clientAnswer.createdAt,
                clientAnswer.questionId,
                clientAnswer.type,
                (clientAnswer as? ClientAnswer.TextAnswer)?.answer,
                (clientAnswer as? ClientAnswer.NumberAnswer)?.answer,
                (clientAnswer as? ClientAnswer.MultiChoiceAnswer)?.answer
            )
        )

    override suspend fun deleteClientRate(clientRate: ClientRate) =
        ratesDao.deleteClientRate(
            ClientRateEntity(
                clientRate.id,
                clientRate.createdAt,
                clientRate.clientName,
                clientRate.answersIds.map { it.id }
            )
        )

}//end of RatesDatabaseRepositoryImpl