package com.cloudsek.practicaljanak.base

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.viewbinding.ViewBinding
import com.cloudsek.practicaljanak.R
import com.cloudsek.practicaljanak.utils.DialogUtils
import com.cloudsek.practicaljanak.utils.PreferenceHelper.customPrefs
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<V : AndroidViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected abstract val modelClass: KClass<V>

    protected abstract fun getViewBinding(): VB

    lateinit var viewModel: V

    protected lateinit var binding: VB

    protected lateinit var prefs: SharedPreferences

    private var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)

        this.viewModel = getViewModel(clazz = modelClass)

        prefs = customPrefs()

        addObservers()

        initView()
        initSetup()
        listeners()
    }

    protected abstract fun initView()

    protected abstract fun listeners()

    protected open fun initSetup() {}

    protected open fun addObservers() {}

    protected open fun removeObservers() {}

    protected fun showProgressDialog(
        message: String? = getString(R.string.default_loading_message),
        isCancelable: Boolean? = false
    ) {
        if (!isFinishing) {
            progressDialog?.dismiss()
            progressDialog = DialogUtils.showProgressDialog(this, message!!, isCancelable)

            if (progressDialog!!.isShowing) {
                progressDialog?.dismiss()
            } else {
                progressDialog?.show()
            }
        }
    }

    protected fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeObservers()
        hideProgressDialog()
    }
}
