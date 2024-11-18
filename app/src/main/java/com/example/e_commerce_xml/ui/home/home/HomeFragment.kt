package com.example.e_commerce_xml.ui.home.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Category
import com.example.domain.model.Products
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.FragmentHomeBinding
import com.example.e_commerce_xml.ui.base.BaseFragment
import com.example.e_commerce_xml.ui.specificProduct.SpecificProductActivity
import com.example.e_commerce_xml.ui.home.CategoriesAdapter
import com.example.e_commerce_xml.ui.home.home.adapters.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>() {

    private val categoriesAdapter = CategoriesAdapter(null)
    
    private val categoryProductsAdapter = ProductsAdapter({position, product ->
        navigateToSpecificProduct(product?.id!!)
    })
    

    private val mostSellingProductsAdapter by lazy { ProductsAdapter({position, product ->
        navigateToSpecificProduct(product?.id!!)
    }) }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
        myObserveLiveData()
        binding.lifecycleOwner = this
    }

    private val mViewModel : HomeContract.ViewModel by viewModels<HomeFragmentViewModel>()

    override fun initViewModel(): HomeFragmentViewModel {
        return  mViewModel as HomeFragmentViewModel
    }
    override fun getLatoutId(): Int {
      return R.layout.fragment_home
    }

    private fun loadData(){
            viewModel.doAction(HomeContract.Action.InitPage)
    }

    private fun initViews(){
        binding.categoriesRv.adapter = categoriesAdapter
        binding.categoryProductsRv.adapter = categoryProductsAdapter
        binding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
    }


    private fun myObserveLiveData() {
        viewModel.event.observe(viewLifecycleOwner,::onEventChange)
        lifecycleScope.launch {
            viewModel.state.collect{
                renderView(it)
            }
        }
    }

    private fun renderView(state : HomeContract.State) {
        when(state){
            is HomeContract.State.Loading ->{
              //  showLoading()
            }
            is HomeContract.State.Success ->{
               // hideLoading()
                state.categoriesList?.let {
                    showCategories(it)
                }
                state.mostSellingProductsList?.let {
                    showMostSellingProducts(it)
                }
                state.categoryProductsList?.let {
                    showCategoryProducts(it)
                }
            }
        }
    }

    private fun onEventChange(event : HomeContract.Event) {
        when(event) {
            is HomeContract.Event.ShowMessage -> {
              //  showSnakeBar(event.message.title ?: "fail in something")
                Log.d("TAG","message in event of snake bar ${event.message.message}")
            }
        }
    }

    private fun showCategories(categories  : List<Category>?) {
        if (categories != null) {
            binding.categoriesShimmerViewContainer.isVisible=false
            categoriesAdapter.changeData(categories)
        }
    }


    private fun showMostSellingProducts(mostSellingProducts : List<Products>?) {
        if(mostSellingProducts != null){
            binding.mostSellingProductsShimmerViewContainer.isVisible=false
            mostSellingProductsAdapter.bindProducts(mostSellingProducts)
        }
    }

    private fun showCategoryProducts(categoryProductsList : List<Products>?) {
        if(categoryProductsList != null){
            binding.categoryProductsShimmerViewContainer.isVisible=false
            categoryProductsAdapter.bindProducts(categoryProductsList)
        }
    }
    private fun navigateToSpecificProduct(productId: String) {
        val intent = Intent(requireActivity(), SpecificProductActivity::class.java).apply {
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }


}




/*
   override fun onResume() {
        super.onResume()
   //   binding.categoriesShimmerViewContainer.startShimmer()
    }
    override fun onPause() {
      // binding.categoriesShimmerViewContainer.stopShimmer()
        super.onPause()
    }
 */

