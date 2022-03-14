package com.ofek.etherscan.searchscreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ofek.etherscan.searchscreen.SearchScreenRepository
import com.ofek.etherscan.searchscreen.ui.models.UiTransaction
import com.ofek.etherscan.searchscreen.ui.models.mapDtoTransactionToUiTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchScreenRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val transactionsLiveData: MutableLiveData<List<UiTransaction>> =
        MutableLiveData(emptyList())


    fun searchTransactions(query: String) {
        repository.getTransactions(query)
            .map { transactions ->
                transactions.map {
                    mapDtoTransactionToUiTransaction(it)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribe { items ->
                transactionsLiveData.value = items
            }
    }

    fun getTransactionsLiveData(): LiveData<List<UiTransaction>> {
        return transactionsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}