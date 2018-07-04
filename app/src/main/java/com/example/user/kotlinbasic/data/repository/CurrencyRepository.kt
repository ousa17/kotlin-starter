package com.example.user.kotlinbasic.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.user.kotlinbasic.data.local.LocalCurrencyDataSource
import com.example.user.kotlinbasic.data.local.entities.CurrencyEntity
import com.example.user.kotlinbasic.data.remote.RemoteCurrencyDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
        private val remoteCurrencyDataSource: RemoteCurrencyDataSource,
        private val localCurrencyDataSource: LocalCurrencyDataSource) {

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()


    fun addCurrencies() {
        val currencyEntityList = localCurrencyDataSource.getAllCurrencies()
        localCurrencyDataSource.insertAll(currencyEntityList)
    }

    fun getCurrencyList(): LiveData<List<CurrencyEntity>> {
        val mutableLiveData = MutableLiveData<List<CurrencyEntity>>()
        val disposable = localCurrencyDataSource.getmAllCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currencyList ->
                    mutableLiveData.value = transform(currencyList)
                }, { t: Throwable? -> t?.printStackTrace() })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    private fun transform(currencies: List<CurrencyEntity>): List<CurrencyEntity> {
        val currencyList = ArrayList<CurrencyEntity>()
        currencies.forEach {
            currencyList.add(CurrencyEntity(it.id, it.countryName,""))
        }
        return currencyList
    }
}

