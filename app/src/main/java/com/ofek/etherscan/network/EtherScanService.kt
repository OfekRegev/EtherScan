package com.ofek.etherscan.network

import com.ofek.etherscan.network.models.SearchResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EtherScanService {

    @GET("api?module=account&action=txlist&startblock=0&endblock=99999999&page=1&offset=10000&sort=asc&apikey=${NetworkConstants.etherscan_api_key}")
    fun getTransactionsForAddress(@Query("address") address: String): Single<SearchResponseDto>


}