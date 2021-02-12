package com.siviwe.safoodprice.providers

import com.siviwe.safoodprice.network.APIService
import javax.inject.Inject

interface ProductsProvider{
    suspend fun provideProducts(shop: String, category: String, page: Int): List<Product>
}

class ProductsProviderImpl @Inject constructor(private val service: APIService): ProductsProvider{
    override suspend fun provideProducts(shop: String, category: String, page: Int): List<Product> {
        return service.getProducts(shop, category, page).body()?.map{
            Product(it.name, it.price, it.imageURL, it.store)
        } ?: listOf()
    }

}

data class Product(
    val name: String?,
    val price: String?,
    val imageURL: String?,
    val store: String?
)
