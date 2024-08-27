package com.example.android_vl_repopattern.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.android_vl_repopattern.R
import com.example.android_vl_repopattern.viewModel.ItemViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB
) : Fragment() {
    private var _binding: VB? = null
    val binding: VB get() = _binding as VB
    val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToMainFragment() {
        findNavController().navigate(R.id.mainFragment)
    }

    fun navigateToEditFragment() {
        findNavController().navigate(R.id.editFragment)
    }

    fun showSnackbar(
        view: View? = getView()?.rootView,
        message: String,
        duration: Int = Snackbar.LENGTH_SHORT) {
        if (view != null) {
            Snackbar.make(view, message, duration).show()
        }
    }
}