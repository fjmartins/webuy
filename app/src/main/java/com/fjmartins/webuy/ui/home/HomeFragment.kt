package com.fjmartins.webuy.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fjmartins.webuy.databinding.HomeFragmentBinding
import com.fjmartins.webuy.model.Listing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.run {
            viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        }
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val columnCount = if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {4} else {3}

        viewModel.listings.observe(viewLifecycleOwner, { listings ->

            val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, columnCount)
            binding.mainRecyclerView.apply {
                layoutManager = mLayoutManager
                adapter = HomeFragmentAdapter(object : OnClickListener {
                    override fun onClick(index: Int, listing: Listing) {
                        findNavController().navigate(HomeFragmentDirections.actionHomeToDetail())

                        Toast.makeText(requireContext(), listing.item_name, Toast.LENGTH_SHORT).show()
                    }
                }, listings)
            }
        })

        viewModel.getListings()
    }

}