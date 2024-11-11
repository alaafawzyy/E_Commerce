package com.example.e_commerce_xml.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.e_commerce_xml.ui.base.Extentions.showDialog


abstract class BaseFragment <VB: ViewBinding,VM: BaseViewModel>:Fragment(){
    lateinit var viewModel: VM
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=initViewModel()
    }
    abstract fun initViewModel():VM
    abstract fun getLatoutId():Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=DataBindingUtil.inflate(inflater,getLatoutId(),container,false)
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}