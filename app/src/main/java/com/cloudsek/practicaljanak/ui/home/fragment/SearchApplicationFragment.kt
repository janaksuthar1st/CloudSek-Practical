package com.cloudsek.practicaljanak.ui.home.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.base.BaseFragment
import com.cloudsek.practicaljanak.common.Resource
import com.cloudsek.practicaljanak.databinding.FragmentSearchApplicationListBinding
import com.cloudsek.practicaljanak.extensions.getClassName
import com.cloudsek.practicaljanak.extensions.nullSafe
import com.cloudsek.practicaljanak.extensions.showToast
import com.cloudsek.practicaljanak.viewmodels.HomeScreenViewModel
import com.networkmodule.model.GetAppAssetsResponse
import kotlin.reflect.KClass

class SearchApplicationFragment :
    BaseFragment<HomeScreenViewModel, FragmentSearchApplicationListBinding>(),
    View.OnClickListener {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class

    override fun getViewBinding(): FragmentSearchApplicationListBinding =
        FragmentSearchApplicationListBinding.inflate(layoutInflater)

    override fun isActivityScopeViewModel(): Boolean = true

    private val fetchApplicationAssetsObserver = Observer<Any> {
        when (it) {
            is Resource.Error<*> -> {
                showToast(context = requireContext(), message = it.message.nullSafe())
            }

            is Resource.Success<*> -> {
                hideProgressDialog()
                val response = it.data as GetAppAssetsResponse
                if (response.assets != null) {
                    if (this.isVisible && this.isAdded && this.isResumed)
                        moveToDetailsScreen(response)
                } else {
                    showToast(
                        context = requireContext(),
                        message = getString(R.string.msg_no_data_found)
                    )
                }
            }

            is Resource.Loading<*> -> {
                it.isLoadingShow.let {
                    if (it as Boolean) {
                        showProgressDialog()
                    } else {
                        hideProgressDialog()
                    }
                }
            }

            is Resource.NoInternetError<*> -> {
                showToast(context = requireContext(), message = it.message.nullSafe())
            }
        }
    }

    override fun initViews(view: View) {

    }

    override fun initSetup() {

    }

    override fun listeners() {
        binding.tvGetAssets.setOnClickListener(this)
        binding.toolbarMain.ivBack.setOnClickListener(this)
    }

    override fun addObservers() {
        viewModel.fetchAppAssetsResponse.observe(viewLifecycleOwner, fetchApplicationAssetsObserver)
    }

    override fun removeObservers() {
        viewModel.fetchAppAssetsResponse.removeObserver(fetchApplicationAssetsObserver)
    }

    private fun moveToDetailsScreen(getAppAssetsResponse: GetAppAssetsResponse) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(
            R.id.flContainer,
            ApplicationDetailsFragment.newInstance(getAppAssetsResponse),
            ApplicationDetailsFragment.getClassName()
        )
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchApplicationFragment().apply {
            arguments = Bundle().apply {
                //No bundle here to use
            }
        }
    }


    override fun onClick(v: View?) {
        when (v) {
            binding.tvGetAssets -> {
                if (binding.etPackageId.text.isNullOrEmpty()) {
                    showToast(
                        requireContext(),
                        getString(R.string.error_please_enter_package_name_first)
                    )
                } else {
                    viewModel.fetchAppAssetsByPackage(binding.etPackageId.text.toString())
                }
            }

            binding.toolbarMain.ivBack -> {
                requireActivity().onBackPressed()
            }
        }
    }
}