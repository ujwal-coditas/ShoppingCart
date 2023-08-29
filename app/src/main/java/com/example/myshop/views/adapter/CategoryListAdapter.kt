package com.example.myshop.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.databinding.ItemCatalogueHomeBinding


class CategoryListAdapter(private val categoryNameList: ArrayList<String>) :
    RecyclerView.Adapter<CategoryListAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemCatalogueHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryNameProduct = binding.txtCategoryName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemCatalogueHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCategoryPosition = categoryNameList[position]
        holder.categoryNameProduct.text = currentCategoryPosition
    }

    override fun getItemCount(): Int {
        return categoryNameList.size
    }
}
