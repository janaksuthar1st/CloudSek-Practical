package com.cloudsek.practicaljanak.ui.home.fragment

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.base.BaseFragment
import com.cloudsek.practicaljanak.common.Resource
import com.cloudsek.practicaljanak.databinding.FragmentMyApplicationListBinding
import com.cloudsek.practicaljanak.extensions.*
import com.cloudsek.practicaljanak.interfaces.OnRecyclerViewItemClicked
import com.cloudsek.practicaljanak.ui.home.adapter.AppInfoListAdapter
import com.cloudsek.practicaljanak.viewmodels.HomeScreenViewModel
import com.networkmodule.model.GetAppAssetsResponse
import kotlin.reflect.KClass


class MyApplicationListFragment : BaseFragment<HomeScreenViewModel, FragmentMyApplicationListBinding>() {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class

    override fun getViewBinding(): FragmentMyApplicationListBinding = FragmentMyApplicationListBinding.inflate(layoutInflater)

    override fun isActivityScopeViewModel(): Boolean = true

    private lateinit var appInfoListAdapter : AppInfoListAdapter
    private var appInfoDetailsList = ArrayList<ApplicationInfo>()

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
        setAdapter()
    }

    override fun initSetup() {
        getAppInstalledApp()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAppInstalledApp() {
        appInfoDetailsList.addAll(requireActivity().packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
        appInfoListAdapter.notifyDataSetChanged()
    }


    private fun setAdapter() {
        appInfoListAdapter = AppInfoListAdapter(appInfoDetailsList, AppListItemClicked())
        binding.rvMyApps.adapter = appInfoListAdapter
    }

    override fun listeners() {
        binding.toolbarMain.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    inner class AppListItemClicked : OnRecyclerViewItemClicked{
        override fun onItemViewClicked(position: Int) {
            viewModel.fetchAppAssetsByPackage(appInfoDetailsList[position].packageName.nullSafe())
        }
    }

    override fun addObservers() {
        viewModel.fetchAppAssetsResponse.observe(viewLifecycleOwner, fetchApplicationAssetsObserver)
    }

    override fun removeObservers() {
        viewModel.fetchAppAssetsResponse.removeObserver(fetchApplicationAssetsObserver)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyApplicationListFragment().apply {
            arguments = Bundle().apply {
                //No bundle here to use
            }
        }
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

}