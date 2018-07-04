package com.example.user.kotlinbasic.data.remote

import javax.inject.Inject

class RemoteCurrencyDataSource @Inject constructor(private val serviceEndpoint: ServiceEndpoint) {

    fun requestAvailableCurrency(currencies: String) = serviceEndpoint.requestAvailableExchange(RemoteContract.ACCESS_KEY_API_LAYER, currencies, RemoteContract.FORMAT_TYPE)
}