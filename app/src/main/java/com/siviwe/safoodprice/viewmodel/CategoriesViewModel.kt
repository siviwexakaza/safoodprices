package com.siviwe.safoodprice.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.siviwe.safoodprice.providers.CategoriesProvider

class CategoriesViewModel @ViewModelInject constructor(private val categoriesProvider: CategoriesProvider): ViewModel() {

    fun getCategories(shop: String) = liveData {
        emit(categoriesProvider.provideCategories(shop))
    }
}
