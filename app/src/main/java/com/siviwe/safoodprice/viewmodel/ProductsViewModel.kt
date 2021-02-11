package com.siviwe.safoodprice.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.siviwe.safoodprice.providers.ProductsProvider

class ProductsViewModel @ViewModelInject constructor(private val productsProvider: ProductsProvider): ViewModel() {

    fun getProducts(shop: String, category: String, page: Int) = liveData {
        emit(productsProvider.provideProducts(shop, category, page))
    }
}