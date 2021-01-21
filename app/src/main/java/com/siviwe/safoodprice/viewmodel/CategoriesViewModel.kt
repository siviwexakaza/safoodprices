package com.siviwe.safoodprice.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siviwe.safoodprice.model.Category
import com.siviwe.safoodprice.network.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel: ViewModel() {

    val categories = MutableLiveData<ArrayList<Category>>()
    val isLoading = MutableLiveData<Boolean>()
    val apiService = APIService()
    val disposable = CompositeDisposable()

    fun refresh(shop: String){
        getRemoteData(shop)
    }

    fun getRemoteData(shop: String){

        isLoading.value = true
        disposable.add(
            apiService.getCategories(shop).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Category>>(){
                    override fun onSuccess(t: List<Category>) {
                        categories.value = ArrayList(t)
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