package com.cloudsek.practicaljanak.ui.home.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.base.BaseFragment
import com.cloudsek.practicaljanak.databinding.FragmentHomeBinding
import com.cloudsek.practicaljanak.extensions.getClassName
import com.cloudsek.practicaljanak.utils.CLOUD_SEK_URL
import com.cloudsek.practicaljanak.viewmodels.HomeScreenViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeScreenViewModel, FragmentHomeBinding>(),
    View.OnClickListener {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun isActivityScopeViewModel(): Boolean = true

    override fun initViews(view: View) {

    }

    override fun initSetup() {

    }

    override fun listeners() {
        binding.tvSearchApplication.setOnClickListener(this)
        binding.tvMyApplications.setOnClickListener(this)
        binding.tvAboutUs.setOnClickListener(this)
        binding.tvExit.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {
                //No bundle here to use
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvSearchApplication -> {
                val fragmentTransaction = fragmentManager?.beginTransaction()

                fragmentTransaction?.add(
                    R.id.flContainer,
                    SearchApplicationFragment.newInstance(),
                    SearchApplicationFragment.getClassName()
                )
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }

            binding.tvMyApplications -> {
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.add(
                    R.id.flContainer,
                    MyApplicationListFragment.newInstance(),
                    MyApplicationListFragment.getClassName()
                )
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }

            binding.tvAboutUs -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(CLOUD_SEK_URL)
                startActivity(intent)
            }

            binding.tvExit -> {
                requireActivity().finish()
            }
        }
    }
}