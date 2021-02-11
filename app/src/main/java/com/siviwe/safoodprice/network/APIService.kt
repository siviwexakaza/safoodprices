package com.siviwe.safoodprice.network

import com.siviwe.safoodprice.model.CategoryResponse
import com.siviwe.safoodprice.model.ProductResponse
import com.siviwe.safoodprice.model.StoreResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface APIService {
    suspend fun getStores(): Response<List<StoreResponse>>
    suspend fun getCategories(shop : String): Response<List<CategoryResponse>>
    suspend fun getProducts(shop: String, category: String, page: Int): Response<List<ProductResponse>>
}

class APIServiceImpl @Inject constructor(): APIService {

    private val BASE_URL = "https://safe-chamber-96261.herokuapp.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(API::class.java)

    override suspend fun getStores(): Response<List<StoreResponse>> {
        return api.getShops()
    }

    override suspend fun getCategories(shop : String): Response<List<CategoryResponse>>{
        return api.getCategories(shop)
    }

    override suspend fun getProducts(
        shop: String,
        category: String,
        page: Int
    ): Response<List<ProductResponse>> {
        return api.getProducts(shop,category, page)
    }
}
