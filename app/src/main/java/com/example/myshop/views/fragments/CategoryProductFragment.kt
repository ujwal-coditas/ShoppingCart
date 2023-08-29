package com.example.myshop.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myshop.R
import com.example.myshop.databinding.FragmentProductCategoryBinding
import com.example.myshop.utils.Constants
import com.example.myshop.utils.Resource
import com.example.myshop.viewmodel.catalogue.CatalogueListViewModel
import com.example.myshop.views.adapter.ProductListAdapter
import com.google.android.material.snackbar.Snackbar


class CategoryProductFragment : Fragment() {
    private lateinit var binding: FragmentProductCategoryBinding
    private val catalogueListViewModel: CatalogueListViewModel by activityViewModels()
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFilter.setOnClickListener {
            fragmentSwitchToCategoryProduct()
        }
          getProductByCategory()
    }
    private fun getProductByCategory() {
        catalogueListViewModel.productByCategoryLiveData
             .observe(
            viewLifecycleOwner,
            Observer { response ->
                when (response) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let {
                            productListAdapter = ProductListAdapter(it)
                            binding.recyclerProductByCategory.adapter = productListAdapter
                            showNumberOfItems()
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.errorMessage?.let {
                            showRetrySnackBarError()
                        }
                    }
                }
            })
    }

    private fun showNumberOfItems() {
        val numberOfItems = productListAdapter.itemCount
        binding.txtNoOfItems.text = getString(R.string.NumberOfTask, numberOfItems)
    }

    private fun showProgressBar() {
        binding.progressBarProductByCategory.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarProductByCategory.visibility = View.INVISIBLE
    }

    private fun showRetrySnackBarError() {
        val retrySnackBar =
            Snackbar.make(
                binding.scrollProductCategory,
                Constants.SNACKBAR_ERROR_MSG,
                Snackbar.LENGTH_INDEFINITE
            )
        retrySnackBar.setAction(Constants.REFRESH_TXT) {
            getProductByCategory()
        }
        retrySnackBar.show()
    }

    private fun fragmentSwitchToCategoryProduct() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_product_category_main_activity, FilterFragment())
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}