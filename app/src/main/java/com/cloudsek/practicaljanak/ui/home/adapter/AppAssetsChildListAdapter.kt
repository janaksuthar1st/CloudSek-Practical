package com.cloudsek.practicaljanak.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.cloudsek.practicaljanak.base.BaseAdapterWithViewBinding
import com.cloudsek.practicaljanak.databinding.ItemAppAssetsBinding
import com.cloudsek.practicaljanak.databinding.ItemAppAssetsChildBinding
import com.cloudsek.practicaljanak.extensions.nullSafe
import com.cloudsek.practicaljanak.model.AssetsData

class AppAssetsChildListAdapter(
    private val assetChildDataList: ArrayList<String>
) : BaseAdapterWithViewBinding(assetChildDataList) {

    override fun getViewBinding(viewType: Int, parent: ViewGroup): ViewBinding {
        return ItemAppAssetsChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val assetDetailsChildListBean = assetChildDataList[position]

        val binding = holder.binding
        if (binding is ItemAppAssetsChildBinding) {
            binding.tvChildTitle.text = assetDetailsChildListBean.nullSafe()
        }
    }
}