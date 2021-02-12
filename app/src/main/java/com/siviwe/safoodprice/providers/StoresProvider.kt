package com.siviwe.safoodprice.providers

import com.siviwe.safoodprice.network.APIService
import javax.inject.Inject

interface StoresProvider {
    suspend fun provideStores(): List<Store>
}

class StoresProviderImpl @Inject constructor(private val service: APIService): StoresProvider {
    override suspend fun provideStores(): List<Store> {
        //TODO - Propagate errors
        return service.getStores().body()?.mapNotNull {
            if (it.color != null && it.name != null && it.route != null) {
                Store(it.name, it.color, it.route)
            } else {
                null
            }
        } ?: listOf()
    }
}

data class Store(val name: String,
                 val color: String,
                 val route: String)
