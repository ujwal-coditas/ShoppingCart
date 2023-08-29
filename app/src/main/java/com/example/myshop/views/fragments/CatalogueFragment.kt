package com.example.myshop.views.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myshop.R
import com.example.myshop.databinding.FragmentCatalogueBinding
import com.example.myshop.utils.Constants
import com.example.myshop.utils.Resource
import com.example.myshop.viewmodel.catalogue.CatalogueListViewModel
import com.example.myshop.views.adapter.CatalogueListAdapter
import com.google.android.material.snackbar.Snackbar

class CatalogueFragment : Fragment() {

    private lateinit var binding: FragmentCatalogueBinding
    private val catalogueListViewModel: CatalogueListViewModel by activityViewModels()
    private lateinit var catalogueListAdapter: CatalogueListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCatalogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCatalogue()

    }

    private fun getCatalogue() {
        catalogueListViewModel.getCatalouge().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {

                        catalogueListAdapter = CatalogueListAdapter(it)
                        binding.recyclerCatalogueFragment.adapter = catalogueListAdapter
                        setCategoryClickListener()
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
        binding.progressBarCatalogue.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarCatalogue.visibility = View.INVISIBLE
    }

    private fun fragmentSwitchToCategoryProduct() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_product_category, CategoryProductFragment())
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
     fun setCategoryClickListener() {
        catalogueListAdapter.setItemClickListener { product ->
            catalogueListViewModel.getProductByPriceFilter(product,00.00,10000.00)
            fragmentSwitchToCategoryProduct()
        }
    }

    private fun showRetrySnackBarError(){
        val retrySnackBar =
            Snackbar.make(binding.clCatalogueFragment, Constants.SNACKBAR_ERROR_MSG, Snackbar.LENGTH_INDEFINITE)
        retrySnackBar.setAction(Constants.REFRESH_TXT) {
           getCatalogue()
        }
        retrySnackBar.show()
    }

}