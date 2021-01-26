package com.siviwe.safoodprice.network

import com.siviwe.safoodprice.model.CategoryResponse
import com.siviwe.safoodprice.model.StoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("shops")
    suspend fun getShops(): Response<List<StoreResponse>>

    @GET("{shop}/categories")
    suspend fun getCategories(@Path("shop") shop: String) : Response<List<CategoryResponse>>
}
