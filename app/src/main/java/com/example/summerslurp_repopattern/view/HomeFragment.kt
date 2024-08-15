package com.example.summerslurp_repopattern.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.summerslurp_repopattern.databinding.FragmentHomeBinding
import com.example.summerslurp_repopattern.model.datamodels.Drink
import com.example.summerslurp_repopattern.view.adapter.DrinkAdapter
import com.example.summerslurp_repopattern.viewModel.MainViewModel

class HomeFragment: Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()
        setupActionButtons()
    }

    private fun setupActionButtons() {
        binding.fabDownload.setOnClickListener {
            viewModel.loadData()
        }

        binding.fabDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun addObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { visibility ->
            binding.progressBar.visibility = visibility
        }

        viewModel.listOfDrinks.observe(viewLifecycleOwner) { listOfDrinks: List<Drink> ->
            binding.rvDrinks.adapter = DrinkAdapter(listOfDrinks, viewModel)
        }
    }
}