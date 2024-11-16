package com.example.e_commerce_xml.ui.login


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Auth.AuthResponce
import com.example.e_commerce_xml.R
import com.example.e_commerce_xml.databinding.FragmentLoginBinding
import com.example.e_commerce_xml.ui.base.BaseFragment
import com.example.e_commerce_xml.utils.Extentions.showDialog
import com.example.e_commerce_xml.ui.main.MainActivity

import com.example.e_commerce_xml.utils.UserDataFiled
import com.example.e_commerce_xml.utils.UserDataUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>(){
    private val mViewModel : LoginContract.ViewModel by viewModels<LoginViewModel>()
    override fun initViewModel(): LoginViewModel {
        return mViewModel as LoginViewModel
    }

    override fun getLatoutId(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    private fun initViews() {
        binding.loginVM = viewModel
        binding.lifecycleOwner = this
        binding.loginBtn.setOnClickListener {
            viewModel.doAction(LoginContract.Action.Login)
        }
    }
    private fun observeData() {
        viewModel.event.observe(viewLifecycleOwner){
            when(it){
                is LoginContract.Event.ShowMessage ->{
                    binding.icNext.isVisible = true
                    binding.progressBar.isVisible = false
                    showDialog(it.message)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.state.collect{
                when(it){
                    is LoginContract.State.Success -> {
                        showSuccessView()
                        navigateToHome(it.user)
                    }
                    is LoginContract.State.Loading -> showLoadingView()


                }
            }
        }
    }

    private fun navigateToHome(userData :AuthResponce) {
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.TOKEN, userData.token)
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.ROLE, userData.user?.role)
        UserDataUtils().saveUserInfo(requireContext(),UserDataFiled.NAME,userData.user?.name)
        UserDataUtils().saveUserInfo(requireContext(),UserDataFiled.EMAIL,userData.user?.email)
        UserDataUtils().saveUserInfo(requireContext(),UserDataFiled.CART_ITEM_COUNT,null)
        startActivity(Intent(activity, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun showSuccessView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showLoadingView() {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
    }

}