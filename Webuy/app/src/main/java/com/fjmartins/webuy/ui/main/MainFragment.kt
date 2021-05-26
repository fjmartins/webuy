package com.fjmartins.webuy.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fjmartins.webuy.databinding.MainFragmentBinding
import com.fjmartins.webuy.model.Listing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.run {
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        }
        binding = MainFragmentBinding.inflate(inflater, container, false)
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
                adapter = MainFragmentAdapter(object : OnClickListener {
                    override fun onClick(index: Int, listing: Listing) {
                        Toast.makeText(requireContext(), listing.item_name, Toast.LENGTH_SHORT).show()
                    }
                }, listings)
            }
        })

        viewModel.getListings()
    }

}