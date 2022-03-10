package com.cloudsek.practicaljanak.base

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.viewbinding.ViewBinding
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.utils.DialogUtils
import com.cloudsek.practicaljanak.utils.PreferenceHelper.customPrefs
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass


abstract class BaseFragment<V : AndroidViewModel, VB : ViewBinding> : Fragment() {

    abstract val modelClass: KClass<V>

    lateinit var viewModel: V

    protected abstract fun getViewBinding(): VB

    protected abstract fun isActivityScopeViewModel(): Boolean

    protected lateinit var binding: VB

    lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isActivityScopeViewModel()){
            this.viewModel = requireActivity().getViewModel(clazz = modelClass)
        }else{
            this.viewModel = getViewModel(clazz = modelClass)
        }


        prefs = requireContext().customPrefs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()

        initViews(view)
        listeners()
        initSetup()
    }

    protected open fun initViews(view: View){}
    protected abstract fun listeners()
    protected open fun initSetup(){}


    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    protected open fun addObservers() {

    }

    open fun removeObservers() {

    }


    override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }


    private var progressDialog: Dialog? = null

    fun showProgressDialog(message: String? = getString(R.string.default_loading_message), isCancelable : Boolean? = false) {
        progressDialog?.dismiss()
        progressDialog = DialogUtils.showProgressDialog(requireContext(), message!!, isCancelable)

        if (progressDialog!!.isShowing) {
            progressDialog?.dismiss()
        } else {
            progressDialog?.show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }
}