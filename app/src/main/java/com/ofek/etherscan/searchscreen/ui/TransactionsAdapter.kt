package com.ofek.etherscan.searchscreen.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ofek.etherscan.databinding.TransactionListItemLayoutBinding
import com.ofek.etherscan.searchscreen.ui.models.UiTransaction

class TransactionsAdapter : RecyclerView.Adapter<TransactionViewHolder>() {

    var transactions = emptyList<UiTransaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.setTransaction(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun onTransactionsChanged(transactions: List<UiTransaction>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }

}

class TransactionViewHolder(
    private val binding: TransactionListItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun setTransaction(
        transaction: UiTransaction
    ) {
        binding.timestamp.text = "timestamp: ${transaction.timeStamp.orEmpty()}"
        binding.from.text = "from: ${transaction.from.orEmpty()}"
        binding.to.text = "to: ${transaction.to.orEmpty()}"
        binding.value.text = "value: ${(transaction.value ?: 0)}"
        binding.confirmations.text = "confirmations: ${transaction.confirmations.orEmpty()}"
        binding.hash.text = "hash: ${transaction.hash.orEmpty()}"
    }
}