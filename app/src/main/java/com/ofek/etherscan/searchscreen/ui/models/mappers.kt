package com.ofek.etherscan.searchscreen.ui.models

import com.ofek.etherscan.network.models.TransactionDto

fun mapDtoTransactionToUiTransaction(
    transactionDto: TransactionDto
) : UiTransaction {
    return UiTransaction(
        transactionDto.timeStamp,
        transactionDto.from,
        transactionDto.to,
        transactionDto.value,
        transactionDto.confirmations,
        transactionDto.hash,
    )
}