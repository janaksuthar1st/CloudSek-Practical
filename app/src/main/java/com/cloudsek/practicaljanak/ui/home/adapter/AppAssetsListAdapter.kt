package com.cloudsek.practicaljanak.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.cloudsek.practicaljanak.base.BaseAdapterWithViewBinding
import com.cloudsek.practicaljanak.databinding.ItemAppAssetsBinding
import com.cloudsek.practicaljanak.model.AssetsData

class AppAssetsListAdapter(
    private val assetDataList: ArrayList<AssetsData>
) : BaseAdapterWithViewBinding(assetDataList) {

    override fun getViewBinding(viewType: Int, parent: ViewGroup): ViewBinding {
        return ItemAppAssetsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val assetDetailsListBean = assetDataList[position]

        val binding = holder.binding
        if (binding is ItemAppAssetsBinding) {
            binding.tvTitle.text = assetDetailsListBean.assetName
            binding.rvAppAssetsList.adapter = assetDetailsListBean.assetList?.let {
                AppAssetsChildListAdapter(
                    it
                )
            }
        }
    }
}