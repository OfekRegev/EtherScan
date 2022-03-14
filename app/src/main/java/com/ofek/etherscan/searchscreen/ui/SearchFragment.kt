package com.ofek.etherscan.searchscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofek.etherscan.databinding.FragmentSearchScreenLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchScreenLayoutBinding
    private lateinit var searchViewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProvider(this)
            .get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            searchViewModel.searchTransactions(binding.searchInputText.text?.toString().orEmpty())
        }
        val adapter = TransactionsAdapter()
        searchViewModel.getTransactionsLiveData().observe(viewLifecycleOwner, {
            adapter.onTransactionsChanged(it)
        })
        binding.transactionsRecyclerView.adapter = adapter
        binding.transactionsRecyclerView.layoutManager = LinearLayoutManager(
            view.context
        )

    }

}