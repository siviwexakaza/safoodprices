package com.siviwe.safoodprice.network

import com.siviwe.safoodprice.model.Category
import com.siviwe.safoodprice.model.Store
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface APIService {
    fun getStores(): Single<List<Store>>
    fun getCategories(shop : String): Single<List<Category>>
}

class APIServiceImpl @Inject constructor(): APIService {

    private val BASE_URL = "https://safe-chamber-96261.herokuapp.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(API::class.java)

    override fun getStores(): Single<List<Store>>{
        return api.getShops()
    }

    override fun getCategories(shop : String): Single<List<Category>>{
        return api.getCategories(shop)
    }
}
