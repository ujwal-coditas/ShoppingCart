package com.example.myshop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myshop.databinding.FragmentHomeBinding
import com.example.myshop.utils.Constants
import com.example.myshop.utils.Resource
import com.example.myshop.viewmodel.category.CategoryListViewModel
import com.example.myshop.viewmodel.product.ProductListViewModel
import com.example.myshop.views.adapter.CategoryListAdapter
import com.example.myshop.views.adapter.ProductListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private  val categoryListViewModel: CategoryListViewModel by viewModels()
    private  val productListViewModel: ProductListViewModel  by viewModels()
    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var productListAdapter: ProductListAdapter

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCategories()
        getProducts()
    }

    private fun getProducts() {
        productListViewModel.getProducts().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        productListAdapter = ProductListAdapter(it)
                        binding.recyclerProductItems.adapter = productListAdapter
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.errorMessage?.let {
                            showRetrySnackBarError()
                    }
                }
            }
        }
    }

    private fun getCategories() {
        categoryListViewModel.getCategory().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        categoryListAdapter = CategoryListAdapter(it)
                        binding.recyclerCatalogue.adapter = categoryListAdapter
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.errorMessage?.let {
                        showRetrySnackBarError()
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressBarHome.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarHome.visibility = View.INVISIBLE
    }
    private fun showRetrySnackBarError(){
        val retrySnackBar =Snackbar.make(binding.scrollHomeFragment, Constants.SNACKBAR_ERROR_MSG, Snackbar.LENGTH_INDEFINITE)
            retrySnackBar.setAction(Constants.REFRESH_TXT) {
                getProducts()
                getCategories()
            }
        retrySnackBar.show()
    }
}