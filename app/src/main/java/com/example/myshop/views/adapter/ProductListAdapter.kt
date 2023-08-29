package com.example.myshop.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.model.ProductListItem
import com.example.myshop.databinding.ItemProductBinding
import com.squareup.picasso.Picasso


class ProductListAdapter(private val productList: ArrayList<ProductListItem>) :
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentProductPosition = productList[position]

        with(currentProductPosition) {
            Picasso.get().load(this.image).placeholder(R.drawable.ic_placeholder_view_vector)
                .into(holder.productImage)
            holder.productName.text = this.title
            holder.productPrice.text = "$${this.price}"
            holder.productRating.rating = this.rating.rate.toFloat()
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class MyViewHolder(binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val productName = binding.txtProductName
        val productPrice = binding.txtProductPrice
        val productImage = binding.imgProductCard
        val productRating = binding.ratingBarProduct
    }
}
