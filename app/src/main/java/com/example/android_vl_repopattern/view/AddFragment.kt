package com.example.android_vl_repopattern.view

import android.os.Bundle
import android.view.View
import com.example.android_vl_repopattern.databinding.FragmentAddBinding
import com.example.android_vl_repopattern.model.dataModels.Item

class AddFragment : BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonAdd()
        setupButtonCancel()
    }

    private fun setupButtonCancel() {
        binding.addBtnCancel.setOnClickListener {
            navigateToMainFragment()
        }
    }

    private fun setupButtonAdd() {
        binding.addBtnAdd.setOnClickListener {
            val title = binding.addTextInputTitle.text.toString()
            if (title.isNotBlank()) {
                viewModel.addNewItem(Item(title = title, image = ""))
                navigateToMainFragment()
            } else {
                showSnackbar(view = binding.root, message = "Please enter a title")
            }
        }
    }
}
