package com.fjmartins.webuy.ui.listing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fjmartins.webuy.databinding.ListingFragmentBinding
import com.fjmartins.webuy.ui.home.HomeViewModel

class ListingFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ListingFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.run {
            viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        }
        binding = ListingFragmentBinding.inflate(inflater, container, false)
        binding.listing = viewModel.selected.value

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}