package com.example.e_commerce_xml.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import com.example.domain.model.Category
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.FragmentHomeBinding
import com.example.e_commerce_xml.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>() {

    private val mviewModel:HomeFragmentViewModel by viewModels()
    override fun initViewModel(): HomeFragmentViewModel {
        return mviewModel
    }
    override fun getLatoutId(): Int {
       return R.layout.fragment_home
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.gettCategories()
        initView()
        observeLiveDataa()
    }


    val adapter = HomeAdapter(null)
    private fun initView() {
        val shimmerFrameLayout = binding.categoryProductsShimmerViewContainer
        shimmerFrameLayout.visibility = View.VISIBLE
        binding.categoriesRv.visibility=View.GONE

        binding.categoriesRv.adapter = adapter }

    private fun observeLiveDataa() {
        viewModel.categories.observe(viewLifecycleOwner){ categories ->
            binding.categoriesShimmerViewContainer.isGone=true
            binding.categoriesRv.visibility=View.VISIBLE
           showCategories(categories)
             } }

    private fun showCategories(categories: List<Category?>?) {
        adapter.changeData(categories) } }
