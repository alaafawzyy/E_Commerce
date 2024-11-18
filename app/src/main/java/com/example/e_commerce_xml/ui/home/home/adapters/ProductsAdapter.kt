package com.example.e_commerce_xml.ui.home.home.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Category
import com.example.domain.model.Products
import com.example.e_commerce_xml.databinding.ItemProductBinding
import com.example.e_commerce_xml.ui.Categories.adapter.SubcategoriesAdapter


class ProductsAdapter(private val onProductClick: (position: Int,product: Products?) -> Unit) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var products: List<Products?> = emptyList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),onProductClick
        )
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onProductClick(position,product)
            notifyDataSetChanged()
    }}

    inner class ViewHolder( val itemProductBinding: ItemProductBinding,private val onCategoryClick: (position: Int,category:Products?) -> Unit) :
        RecyclerView.ViewHolder(itemProductBinding.root) {

        fun bind(product: Products?) {
            //itemProductBinding.product = product

            Glide.with(itemView).load(product?.imageCover).into(itemProductBinding.productImg)
            itemProductBinding.executePendingBindings()
            if (product?.priceAfterDiscount != null) {
                itemProductBinding.productPrice.text = "EGP ${product?.priceAfterDiscount}"
                itemProductBinding.productOldPrice.isVisible = true
                itemProductBinding.productOldPrice.text = "EGP ${product?.price}"
                itemProductBinding.productOldPrice.paintFlags =
                    itemProductBinding.productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                itemProductBinding.productPrice.text = "EGP ${product?.price}"
                itemProductBinding.productOldPrice.isVisible = false
            }
            itemProductBinding.reviewValueTv.text = "(${product?.ratingsAverage})"


            itemView.setOnClickListener {
                itemView.isEnabled = false
                if (product != null) {
                    onCategoryClick(position,product)
                }
                itemView.postDelayed({ itemView.isEnabled = true }, 500) }
        }
    }

    fun bindProducts(products: List<Products?>) {
        this.products = products
        notifyDataSetChanged()
    }





}
