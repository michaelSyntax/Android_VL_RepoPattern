package com.example.summerslurp_repopattern.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.summerslurp_repopattern.R
import com.example.summerslurp_repopattern.databinding.FragmentDrinkDetailBinding
import com.example.summerslurp_repopattern.databinding.FragmentHomeBinding
import com.example.summerslurp_repopattern.viewModel.MainViewModel


class DrinkDetailFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDrinkDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedDrink.observe(viewLifecycleOwner) {
            binding.tvDetail.text = it.name
        }
    }
}