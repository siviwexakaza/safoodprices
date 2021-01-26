package com.siviwe.safoodprice.providers

import com.siviwe.safoodprice.network.APIService
import javax.inject.Inject

interface CategoriesProvider {
    suspend fun provideCategories(shop: String): List<Category>
}

class CategoriesProviderImpl @Inject constructor(private val service: APIService): CategoriesProvider {
    override suspend fun provideCategories(shop: String): List<Category> {
        //TODO - Propagate errors
        return service.getCategories(shop).body()?.map {
            Category(it.name)
        } ?: listOf()
    }
}

data class Category(val name: String?)
