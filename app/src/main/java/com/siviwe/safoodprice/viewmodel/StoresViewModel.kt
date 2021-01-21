package com.siviwe.safoodprice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siviwe.safoodprice.model.Store
import com.siviwe.safoodprice.network.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class StoresViewModel: ViewModel() {

    val stores = MutableLiveData<ArrayList<Store>>()
    val isLoading = MutableLiveData<Boolean>()

    private val apiSevice = APIService()
    private val disposable = CompositeDisposable()

    fun refresh(){
        getRemoteData()
    }

    private fun getRemoteData(){
        isLoading.value = true

        disposable.add(
                apiSevice.getStores().subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object: DisposableSingleObserver<List<Store>>(){
                            override fun onSuccess(t: List<Store>) {
                                stores.value = ArrayList(t)
                                isLoading.value = false
                            }

                            override fun onError(e: Throwable) {
                                isLoading.value = false
                                e.printStackTrace()
                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}