package com.cloudsek.practicaljanak.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView

import com.cloudsek.practicaljanak.R


object DialogUtils {

    fun showProgressDialog(
        context: Context, message: String, isCancelable: Boolean? = false
    ): Dialog {
        val progressDialog = Dialog(context)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.view_custom_progress_dialog)
        progressDialog.setCancelable(isCancelable!!)
        progressDialog.findViewById<AppCompatTextView>(R.id.tvTextMessage)?.text = message
        return progressDialog
    }

    fun showOkDialog(
        context: Context,
        title: String = context.getString(R.string.app_name),
        message: String,
        isFinish: Boolean = false
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(context.getString(R.string.btn_text_ok)) { dialog, _ ->
            if (isFinish) {
                val activity = context as Activity
                activity.finish()
            } else {
                dialog.dismiss()
            }

        }
        alertDialog.show()
    }
}