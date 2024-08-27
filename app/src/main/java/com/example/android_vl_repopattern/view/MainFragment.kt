package com.example.android_vl_repopattern.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.android_vl_repopattern.databinding.FragmentMainBinding
import com.example.android_vl_repopattern.view.adapter.ItemApter
import com.example.android_vl_repopattern.viewModel.ItemViewModel

class MainFragment: BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFloatingActionButtons()
        setupAdapter()
        addObservers()
    }

    private fun setupAdapter() {
        PagerSnapHelper().attachToRecyclerView(binding.rvItems)
        val selectItem: (Long) -> Unit = { viewModel.selectItemById(it) }
        binding.rvItems.adapter = ItemApter(selectItem, ::navigateToEditFragment)
    }

    private fun setupFloatingActionButtons() {
        binding.fabDelete.setOnClickListener {
            viewModel.deleteAllItems()
        }

        binding.fabDownload.setOnClickListener {
            viewModel.loadData()
        }
    }

    private fun addObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { visibility ->
            binding.progressBar.visibility = visibility
        }

        viewModel.items.observe(viewLifecycleOwner) { items ->
            (binding.rvItems.adapter as ItemApter).submitList(items)
        }
    }
}