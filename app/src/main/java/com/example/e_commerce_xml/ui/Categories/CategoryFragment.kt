package com.example.e_commerce_xml.ui.Categories


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.domain.model.Category
import com.example.domain.model.SubCategory
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.FragmentCategoriesBinding
import com.example.e_commerce_xml.ui.base.BaseFragment
import com.example.e_commerce_xml.ui.home.CategoryAdapter
import com.example.e_commerce_xml.ui.Categories.adapter.SubcategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class CategoryFragment() : BaseFragment<FragmentCategoriesBinding, CategoryViewHolder>() {
      private  var _position: Int? =null
    private val categoryAdapter = CategoryAdapter(null,{ position ,category->
        viewModel.onCategorySelected(category?.id!!)
        initCategory(category)
    })

    private fun initCategory(category: Category?) {
            Glide.with(this).load(category?.image)
                .centerCrop()
                .into(binding.cardBgImv)
            binding.selectedCategoryName.text = category?.name


    }

    private val subCategoryAdapter = SubcategoriesAdapter(null)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
        myObserveLiveData()
    }

    private val mViewModel : CategoryContract.ViewModel by viewModels<CategoryViewHolder>()

    override fun initViewModel(): CategoryViewHolder{
        return  mViewModel as CategoryViewHolder
    }
    override fun getLatoutId(): Int {
        return R.layout.fragment_categories
    }

    private fun loadData(){
        viewModel.doAction(CategoryContract.Action.InitPage)
    }


    private fun initViews(){
        binding.categoriesRv.adapter = categoryAdapter
        binding.subcategoryRv.adapter=subCategoryAdapter

    }


    private fun myObserveLiveData() {
        lifecycleScope.launch {
            viewModel.state.collect{
                renderView(it)
            }
        }
    }


    private fun renderView(state : CategoryContract.State) {
        when(state){
            is CategoryContract.State.Loading ->{
                //  showLoading()
            }
            is CategoryContract.State.Success ->{
                state.categoriesList?.let {
                    showCategories(it)
                }

            }
            is CategoryContract.State.SubCategory ->{
                state.subCategoriesList?.let {
                    Log.d("alaaa","alaaa")

                    showSubCategories(it)
                }

            }
        }
    }

    private fun showSubCategories(it: List<SubCategory>) {
        if (it != null) {
       subCategoryAdapter.bindSubcategories(it)
            Log.d("alaa","alaa")
    }}

    private fun showCategories(categories  : List<Category>?) {
        if (categories != null) {
            binding.categoriesShimmerViewContainer.isVisible=false
            categoryAdapter.changeData(categories)
        }
    }

}
