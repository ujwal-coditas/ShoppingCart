package com.example.myshop.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myshop.databinding.ActivityFilterBinding
import com.example.myshop.utils.Constants
import com.example.myshop.viewmodel.catalogue.CatalogueListViewModel

class FilterFragment : Fragment() {

    private lateinit var binding: ActivityFilterBinding
    private val catalogueListViewModel: CatalogueListViewModel by activityViewModels()
    private var startRangeValue: Double = 00.00
    private var endRangeValue: Double = 10000.00
    lateinit var productCategory: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rangeSliderValueToTxt()
        rangeSliderValueApplyResult()
    }

    private fun rangeSliderValueToTxt() {
        binding.rsPriceRange.addOnChangeListener { slider, value, fromUser ->
            val valuesOfRange = binding.rsPriceRange.values
            startRangeValue = valuesOfRange[0].toDouble()
            endRangeValue = valuesOfRange[1].toDouble()
            binding.txtLowerPriceRange.text = String.format("%.02f", startRangeValue)
            binding.txtUpperPriceRange.text = String.format("%.02f", endRangeValue)
        }
    }

    private fun rangeSliderValueApplyResult() {
        binding.rsPriceRange.addOnChangeListener { slider, value, fromUser ->
            val valuesOfRange = binding.rsPriceRange.values
            startRangeValue = valuesOfRange[0].toDouble()
            endRangeValue = valuesOfRange[1].toDouble()
            binding.btnResult.setOnClickListener {
                catalogueListViewModel.productByCategoryLiveData
                    .observe(
                        viewLifecycleOwner,
                        Observer { response ->
                            response.data?.let {
                                if (it.size != 0) {
                                    productCategory = it[0].category

                                    catalogueListViewModel.getProductByPriceFilter(
                                        productCategory,
                                        startRangeValue,
                                        endRangeValue
                                    )
                                    requireActivity().onBackPressed()

                                } else {
                                    catalogueListViewModel.getProductByPriceFilter(
                                        productCategory,
                                        00.00,
                                        1000.00
                                    )
                                    Toast.makeText(
                                        activity,
                                        Constants.NO_PRODUCT_FOUND_MSG,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        })
            }
        }

    }

}