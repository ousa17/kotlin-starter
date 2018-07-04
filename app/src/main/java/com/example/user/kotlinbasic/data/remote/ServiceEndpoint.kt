package com.example.user.kotlinbasic.data.remote

import com.example.user.kotlinbasic.data.remote.responses.CurrencyResponse
import com.example.user.kotlinbasic.data.remote.RemoteContract
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceEndpoint {

    @GET(RemoteContract.LIVE)
    fun requestAvailableExchange(
            @Query(RemoteContract.ACCESS_KEY) accessKey: String,
            @Query(RemoteContract.CURRENCIES) currencies: String,
            @Query(RemoteContract.FORMAT) format: String
    ): Observable<CurrencyResponse>
}