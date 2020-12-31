package com.siviwe.safoodprice.network

import com.siviwe.safoodprice.model.Store
import io.reactivex.Single
import retrofit2.http.GET

interface StoreAPI {
    @GET("shops")
    fun getShops(): Single<List<Store>>
}