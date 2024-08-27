package com.example.android_vl_repopattern.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.android_vl_repopattern.R
import com.example.android_vl_repopattern.databinding.FragmentEditBinding
import com.example.android_vl_repopattern.viewModel.ItemViewModel

class EditFragment : BaseFragment<FragmentEditBinding>(FragmentEditBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setupSaveButton()
        setupDeleteButton()
    }

    private fun setupSaveButton() {
        binding.editBtnSave.setOnClickListener {
            val title = binding.editTextInputName.text.toString()
            viewModel.updateItem(title)
            navigateToMainFragment()
        }
    }

    private fun setupDeleteButton() {
        binding.editBtnDelete.setOnClickListener {
            viewModel.deleteItemById()
            navigateToMainFragment()
        }
    }

    private fun addObservers() {
        viewModel.selectedItem.observe(viewLifecycleOwner) {
            binding.editTextInputName.setText("it.title")
            binding.editIvContact.load("it.image") {
                error(R.drawable.ic_round_broken_image_24)
                transformations(RoundedCornersTransformation(10f))
            }
        }
    }
}
