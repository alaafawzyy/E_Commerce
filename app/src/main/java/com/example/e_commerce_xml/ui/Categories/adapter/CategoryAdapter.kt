package com.example.e_commerce_xml.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Category
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.ItemCategoryRectBinding

class CategoryAdapter(
    var categoriesList: List<Category?>?,
     private val onCategoryClick: (position: Int,category: Category?) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding = ItemCategoryRectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding,onCategoryClick)
    }

    override fun getItemCount(): Int {
        return categoriesList?.size ?: 0
    }

    private var selectedPosition: Int = -1
    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val category = categoriesList?.get(position)
        val isSelected = position == selectedPosition
        holder.Bind(category, isSelected)
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            onCategoryClick(position,category)
            notifyItemChanged(previousPosition)
            notifyItemChanged(position)
        } }


    class CategoryViewHolder(val binding: ItemCategoryRectBinding,  private val onCategoryClick: (position: Int,category:Category?) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun Bind(category: Category?, isSelected: Boolean) {
            binding.categoryName.text = category?.name ?: "No Category"
            binding.draggingBar.isVisible = isSelected

            itemView.setOnClickListener {
                itemView.isEnabled = false
                if (category != null) {
                    onCategoryClick(position,category)
                }
                itemView.postDelayed({ itemView.isEnabled = true }, 500) }

        } }

    fun changeData(articles: List<Category?>?) {
        if (articles != null) {
            categoriesList = articles
            notifyDataSetChanged()
        }
    }

}
