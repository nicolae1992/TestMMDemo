package com.appenginex.com.data.repository

import com.appenginex.com.data.extension.toTransaction
import com.appenginex.com.data.model.Result
import com.appenginex.com.model.Transaction
import com.appenginex.com.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val apiService: ApiService) {
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