package com.example.e_commerce_xml.ui.specificProduct

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.domain.model.Products
import com.example.e_commerce_xml.databinding.ActivitySpeificProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecificProductActivity : AppCompatActivity() {

    lateinit var binding:ActivitySpeificProductBinding
    private val mviewModel: SpecificProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySpeificProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.getStringExtra("PRODUCT_ID")
        productId?.let { id ->
            mviewModel.getProduct(id)
        } ?: run {
            Toast.makeText(this, "Product ID not found!", Toast.LENGTH_SHORT).show()
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            mviewModel.product.collect { product ->
                product?.let {
                    updateUI(it)
                } ?: run {
                    Toast.makeText(this@SpecificProductActivity, "Error fetching product data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUI(product: Products) {
        Glide.with(this).load(product?.imageCover).into(binding.productImg)
        binding.productName.text=product.title
        binding.productPrice.text= "$ ${product.price}.99"
        binding.productDescriptionContnt.text=product.description

    }
}


