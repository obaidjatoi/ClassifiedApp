package com.android.classifiedd.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.classifiedd.presentation.adapters.ListingAdapter
import com.android.classifiedd.databinding.ListingBinding
import com.android.classifiedd.data.models.ProductListing
import com.android.classifiedd.presentation.navigateToDetails
import com.android.classifiedd.presentation.viewmodels.ProductsViewModel

class ProductListing : Fragment(), ListingAdapter.OnItemClick {
    private val productsViewModel: ProductsViewModel by viewModels()
    private lateinit var binding: ListingBinding
    private lateinit var listingAdapter: ListingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModel.getProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsViewModel.screenState.observe(viewLifecycleOwner, { screen ->
            when (screen) {
                ScreenState.ERROR -> {
                    manageScreen(-1)
                }
                ScreenState.NETWORK_ERROR -> {
                    manageScreen(-1)
                    Toast.makeText(context, "Please turn your internet ON", Toast.LENGTH_LONG)
                        .show()
                }
                ScreenState.DATA_LOADED -> {
                    setAdapterAndShowData()
                }
                ScreenState.DATA_LOADING -> {
                    manageScreen(0)
                }
            }
        })
    }

    private fun manageScreen(state: Int) {
        when (state) {
            -1 -> {
                binding.loading.visibility = View.GONE
                binding.listing.visibility = View.GONE
                binding.error.visibility = View.VISIBLE
            }
            0 -> {
                binding.loading.visibility = View.VISIBLE
                binding.listing.visibility = View.GONE
                binding.error.visibility = View.GONE
            }
            else -> {
                binding.loading.visibility = View.GONE
                binding.listing.visibility = View.VISIBLE
                binding.error.visibility = View.GONE
            }
        }
    }

    private fun setAdapterAndShowData() {
        manageScreen(1)
        binding.listing.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        listingAdapter = ListingAdapter(productsViewModel.data, this)
        binding.listing.adapter = listingAdapter
    }


    override fun onItemPressed(product: ProductListing) {
        navigateToDetails(findNavController(), product)
    }

}