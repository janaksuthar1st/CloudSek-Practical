package com.cloudsek.practicaljanak.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.base.BaseFragment
import com.cloudsek.practicaljanak.databinding.FragmentApplicationDetailsBinding
import com.cloudsek.practicaljanak.extensions.nullSafe
import com.cloudsek.practicaljanak.extensions.showToast
import com.cloudsek.practicaljanak.model.AssetsData
import com.cloudsek.practicaljanak.ui.home.adapter.AppAssetsListAdapter
import com.cloudsek.practicaljanak.ui.home.adapter.AppInfoListAdapter
import com.cloudsek.practicaljanak.utils.BUNDLE_KEY_DATA
import com.cloudsek.practicaljanak.utils.CommonUtils.checkInternetConnected
import com.cloudsek.practicaljanak.viewmodels.HomeScreenViewModel
import com.networkmodule.model.GetAppAssetsResponse
import kotlin.reflect.KClass

class ApplicationDetailsFragment :
    BaseFragment<HomeScreenViewModel, FragmentApplicationDetailsBinding>() {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class

    override fun getViewBinding(): FragmentApplicationDetailsBinding =
        FragmentApplicationDetailsBinding.inflate(layoutInflater)

    override fun isActivityScopeViewModel(): Boolean = true

    private lateinit var getAppAssetsResponse: GetAppAssetsResponse
    private lateinit var appAssetsListAdapter: AppAssetsListAdapter
    private var assetList = ArrayList<AssetsData>()

    override fun initViews(view: View) {
        getBundleValues()
        if (!requireContext().checkInternetConnected()) {
            showToast(
                requireContext(),
                getString(R.string.msg_no_internet_home_screen)
            )
        }
        setAdapter()
        renderAssetsIntoList()
        setData(getAppAssetsResponse)
    }

    private fun getBundleValues() {
        getAppAssetsResponse =
            this.arguments?.getParcelable<GetAppAssetsResponse>(BUNDLE_KEY_DATA) as GetAppAssetsResponse
    }

    private fun setAdapter() {
        appAssetsListAdapter = AppAssetsListAdapter(assetList)
        binding.rvAssets.adapter = appAssetsListAdapter
    }

    private fun renderAssetsIntoList() : ArrayList<AssetsData>{
        assetList.clear()
        if (!getAppAssetsResponse.assets?.amazonExecuteAPIURL?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_amazon_execute_api_url),getAppAssetsResponse.assets?.amazonExecuteAPIURL))
        }
        if (!getAppAssetsResponse.assets?.email?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_email),getAppAssetsResponse.assets?.email))
        }
        if (!getAppAssetsResponse.assets?.filename?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_file_name),getAppAssetsResponse.assets?.filename))
        }
        if (!getAppAssetsResponse.assets?.filePath?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_file_path),getAppAssetsResponse.assets?.filePath))
        }
        if (!getAppAssetsResponse.assets?.host?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_host),getAppAssetsResponse.assets?.host))
        }
        if (!getAppAssetsResponse.assets?.iPAddressDisclosure?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_ip_address),getAppAssetsResponse.assets?.iPAddressDisclosure))
        }
        if (!getAppAssetsResponse.assets?.restApi?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_rest_api),getAppAssetsResponse.assets?.restApi))
        }
        if (!getAppAssetsResponse.assets?.url?.isEmpty().nullSafe()){
            assetList.add(AssetsData(getString(R.string.title_url),getAppAssetsResponse.assets?.url))
        }

        return assetList
    }

    override fun listeners() {
        binding.toolbarMain.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(getAppAssetsResponse: GetAppAssetsResponse) {
        binding.tvPackageName.text = getAppAssetsResponse.packageId.nullSafe()
        appAssetsListAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(getAppAssetsResponse: GetAppAssetsResponse) =
            ApplicationDetailsFragment().apply {
                arguments = Bundle().apply { //No bundle here to use
                    this.putParcelable(BUNDLE_KEY_DATA, getAppAssetsResponse)
                }
            }
    }
}