package com.appenginex.com.data.repository

import com.appenginex.com.data.extension.toCreditCard
import com.appenginex.com.data.extension.toCreditCardEntity
import com.appenginex.com.data.extension.toDeleteCreditCard
import com.appenginex.com.data.extension.toPostCreditCard
import com.appenginex.com.data.extension.toTransaction
import com.appenginex.com.data.model.Result
import com.appenginex.com.database.dao.CreditCardDao
import com.appenginex.com.model.CreditCard
import com.appenginex.com.model.CreditCardInfo
import com.appenginex.com.model.DeleteCreditCard
import com.appenginex.com.model.Transaction
import com.appenginex.com.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val apiService: ApiService,
    private val creditCardDao: CreditCardDao
) {

    suspend fun addCreditCardStorage(creditCard: CreditCard) {
        creditCardDao.insertUser(creditCard.toCreditCardEntity())
    }

    fun getCreditCards(): Flow<List<CreditCard>> {
        return creditCardDao.getCards()
            .map { entityList ->
                entityList.map { it.toCreditCard() }
            }
    }

    fun addCreditCard(amount: String): Flow<Result<CreditCardInfo>> = flow {
        try {
            val response = apiService.addCreditCard(amount)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Result.Success(body.toPostCreditCard()))
                } else {
                    emit(Result.Error("Response body is null"))
                }
            } else {
                emit(Result.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Exception: ${e.message}"))
        }
    }

    fun deleteCreditCard(amount: String): Flow<Result<DeleteCreditCard>> = flow {
        try {
            val response = apiService.deleteCreditCard(amount)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Result.Success(body.toDeleteCreditCard()))
                } else {
                    emit(Result.Error("Response body is null"))
                }
            } else {
                emit(Result.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Exception: ${e.message}"))
        }
    }

    fun transactionAmount(
        from: String,
        to: String,
        amount: String
    ): Flow<Result<Transaction>> = flow {
        try {
            val response = apiService.addTransaction(from = from, to = to, amount = amount)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Result.Success(body.toTransaction()))
                } else {
                    emit(Result.Error("Response body is null"))
                }
            } else {
                emit(Result.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Exception: ${e.message}"))
        }
    }
}