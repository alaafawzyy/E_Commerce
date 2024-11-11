package com.example.e_commerce_xml.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Category
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.FragmentHomeBinding
import com.example.e_commerce_xml.ui.base.BaseFragment
import com.example.e_commerce_xml.ui.base.Extentions.showDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>() {

    private val mviewModel:HomeContract.ViewModel by viewModels<HomeFragmentViewModel>()
    override fun initViewModel(): HomeFragmentViewModel {
        return mviewModel as HomeFragmentViewModel
    }

    override fun getLatoutId(): Int {
       return R.layout.fragment_home
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doAction(HomeContract.Action.InitPage)
        initView()
        observeLiveDataa()
    }

    val adapter = HomeAdapter(null)
    private fun initView() {
        binding.categoriesShimmerViewContainer.visibility = View.VISIBLE
        binding.categoriesRv.visibility=View.GONE
        binding.categoriesRv.adapter = adapter }

    private fun observeLiveDataa() {
        viewModel.event.observe(viewLifecycleOwner,::onEventChang)
        lifecycleScope.launch {
            viewModel.state.collect{
                renderViewState(it)
            } } }

    private fun onEventChang(event: HomeContract.Event) {
            when (event){
                is HomeContract.Event.showMessage ->{
                    showDialog(event.viewMessage)
                }
            } }


    private fun renderViewState(state: HomeContract.State) {
        when (state) {
            is HomeContract.State.Succes -> {
                showCategories(state.categories)
            }
            is HomeContract.State.Loading -> {
                showLoadingState()
            } } }

    private fun showCategories(categories: List<Category?>?) {
        categories.let {
            binding.categoriesShimmerViewContainer.isGone=true
            binding.categoriesRv.visibility=View.VISIBLE
            adapter.changeData(it)
        } }

    private fun showLoadingState() {
        binding.categoriesShimmerViewContainer.isVisible=true }

}
