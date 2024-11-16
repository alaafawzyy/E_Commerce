package com.example.e_commerce_xml.ui.Categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.SubCategory
import com.example.e_commerce_xml.databinding.ItemSubcategoryBinding



class SubcategoriesAdapter(private var subcategories: List<SubCategory?>? = null) :
    RecyclerView.Adapter<SubcategoriesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSubcategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun getItemCount(): Int {
        return subcategories?.size?:0
    }


    //  override fun getItemCount(): Int = subcategories?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subcategory = subcategories?.get(position)
        holder.bind(subcategory)
        /*
        subcategoryClicked?.let { subcategoryClicked ->
            holder.itemView.setOnClickListener {
                subcategoryClicked.invoke(position, subcategory)
            }

         */
        }

    fun bindSubcategories(subcategories: List<SubCategory>?) {
        this.subcategories = subcategories
        notifyDataSetChanged()
    }

class ViewHolder(private val itemSubcategoryBinding: ItemSubcategoryBinding) :
    RecyclerView.ViewHolder(itemSubcategoryBinding.root) {
    fun bind(subcategory: SubCategory?) {
        itemSubcategoryBinding.subcategoryName.text = subcategory?.name
        // itemSubcategoryBinding.executePendingBindings( )

    }


    //var subcategoryClicked: ((position: Int, category: Subcategory) -> Unit)? = null

}}
