package com.cloudsek.practicaljanak.ui.home.adapter

import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.cloudsek.practicaljanak.base.BaseAdapterWithViewBinding
import com.cloudsek.practicaljanak.databinding.ItemMyAppsBinding
import com.cloudsek.practicaljanak.extensions.nullSafe
import com.cloudsek.practicaljanak.interfaces.OnRecyclerViewItemClicked

class AppInfoListAdapter(
    private val appInfoDetailsList: ArrayList<ApplicationInfo>,
    private val onItemClick: OnRecyclerViewItemClicked
) : BaseAdapterWithViewBinding(appInfoDetailsList) {

    override fun getViewBinding(viewType: Int, parent: ViewGroup): ViewBinding {
        return ItemMyAppsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val appInfoDetailsListBean = appInfoDetailsList[position]

        val binding = holder.binding
        if (binding is ItemMyAppsBinding) {
            binding.tvPackageName.text = appInfoDetailsListBean.packageName.nullSafe()
            binding.tvTargetSDK.text = appInfoDetailsListBean.targetSdkVersion.nullSafe().toString()
        }

        holder.itemView.rootView.setOnClickListener {
            onItemClick.onItemViewClicked(holder.adapterPosition)
        }
    }
}