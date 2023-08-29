package com.example.myshop.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.databinding.ItemCatalougeBinding


class CatalogueListAdapter(private val catalogueNameList: ArrayList<String>) :
    RecyclerView.Adapter<CatalogueListAdapter.MyViewHolder>() {

    class MyViewHolder(binding: ItemCatalougeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val catalogueNameProduct = binding.txtCatalogueName
        val catalogueCard = binding.constraintLayoutCatalogueCard
    }

    private var onItemClickListener: ((product:String)->Unit)?=null
    fun setItemClickListener(listener: (product:String)-> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemCatalougeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCategoryPosition = catalogueNameList[position]
        holder.catalogueNameProduct.text = currentCategoryPosition
        holder.catalogueCard.setOnClickListener {
            onItemClickListener?.let {
                it(currentCategoryPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return catalogueNameList.size
    }
}
