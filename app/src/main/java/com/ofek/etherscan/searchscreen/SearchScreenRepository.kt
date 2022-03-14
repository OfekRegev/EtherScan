package com.ofek.etherscan.searchscreen

import com.ofek.etherscan.network.EtherScanService
import com.ofek.etherscan.network.models.TransactionDto
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchScreenRepository @Inject constructor (
    private val service: EtherScanService,
) {

    fun getTransactions(query: String): Single<List<TransactionDto>> {
        return service.getTransactionsForAddress(query).map {
            it.transactions.orEmpty()
        }
    }
}