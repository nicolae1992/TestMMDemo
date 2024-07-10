package com.appenginex.com.network

import com.appenginex.com.network.model.NetworkAddCreditCard
import com.appenginex.com.network.model.NetworkDeleteCard
import com.appenginex.com.network.model.NetworkTransaction
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/add_card")
    suspend fun addCreditCard(
        @Field("amount") amount: String?
    ): Response<NetworkAddCreditCard>

    @FormUrlEncoded
    @POST("api/delete_card")
    suspend fun deleteCreditCard(
        @Field("card_id") idCard: String
    ): Response<NetworkDeleteCard>

    @FormUrlEncoded
    @POST("api/transaction")
    suspend fun addTransaction(
        @Field("from") from: String,
        @Field("to") to: String,
        @Field("amount") amount: String,
    ): Response<NetworkTransaction>
}