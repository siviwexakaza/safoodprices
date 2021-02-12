package com.siviwe.safoodprice.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.siviwe.safoodprice.providers.Store
import com.siviwe.safoodprice.providers.StoresProvider

class StoresViewModel @ViewModelInject constructor(private val storesProvider: StoresProvider): ViewModel() {
    var selectedStore: Store? = null

    fun getStores() = liveData {
        emit(storesProvider.provideStores())
    }
}
