package com.example.e_commerce_xml.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.domain.model.Category
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.ItemCategoryBinding

class CategoriesAdapter(var categoriesList:List<Category?>?): RecyclerView.Adapter<CategoriesAdapter.HomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itembinding=ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return HomeViewHolder(itembinding)
    }


    override fun getItemCount(): Int {
        return categoriesList?.size ?: 0
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val newsItem = categoriesList?.get(position)
        holder.Bind(newsItem)
    }



    fun changeData(articles: List<Category?>?) {
        if (articles !=null){
            categoriesList = articles
            notifyDataSetChanged()
        }}


    class HomeViewHolder(val binding:ItemCategoryBinding ) : ViewHolder(binding.root) {
        fun Bind(category: Category?) {
            binding.categoryName.text=category?.name ?:"No Category"
            Glide.with(itemView).load(category?.image).circleCrop().into(binding.categoryImg) } }

}