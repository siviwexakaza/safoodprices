package com.siviwe.safoodprice.network

import com.siviwe.safoodprice.model.Category
import com.siviwe.safoodprice.model.Store
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("shops")
    fun getShops(): Single<List<Store>>

    @GET("{shop}/categories")
    fun getCategories(@Path("shop") shop: String) : Single<List<Category>>
}