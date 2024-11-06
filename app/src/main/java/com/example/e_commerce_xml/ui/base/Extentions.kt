package com.example.e_commerce_xml.ui.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

object Extentions {
    private fun showDialog(
        context: Context,
        message: String? = null,
        positiveButton: String? = "OK", // تعيين قيمة افتراضية
        postActionCallBack: (() -> Unit)? = null,
        negativeButton: String? = null
    ): AlertDialog {
        val alertDialog = AlertDialog.Builder( context)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton(positiveButton) { dialogInterface, _ ->
            postActionCallBack?.invoke()
            dialogInterface.dismiss()
        }

        negativeButton?.let {
            alertDialog.setNegativeButton(it) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }

        return alertDialog.show()
    }

    fun Fragment.showDialog(
        message: String? = null,
        positiveButton: String? = "OK",
        postActionCallBack: (() -> Unit)? = null,
        negativeButton: String? = null
    ): AlertDialog {
        return showDialog(requireContext(),message,positiveButton,postActionCallBack,negativeButton)
    }

    fun Fragment.showDialog(
        viewMessage: ViewMessage
    ): AlertDialog {
        return showDialog(
            message = viewMessage.message,
            positiveButton = viewMessage.posActionName,
            postActionCallBack = viewMessage.posActionCallBack,
            negativeButton = viewMessage.negActionName
        )
    }



    fun Activity.showDialog(
        message: String? = null,
        positiveButton: String? = "OK", // تعيين قيمة افتراضية
        postActionCallBack: (() -> Unit)? = null,
        negativeButton: String? = null
    ): AlertDialog {
        return showDialog(this,message,positiveButton,postActionCallBack,negativeButton)
    }

    fun Activity.showDialog(
        viewMessage: ViewMessage
    ): AlertDialog {
        return showDialog(
            message = viewMessage.message,
            positiveButton = viewMessage.posActionName,
            postActionCallBack = viewMessage.posActionCallBack,
            negativeButton = viewMessage.negActionName
        )
    }

}


