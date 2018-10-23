package com.jquery404.foodie.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.view.View

abstract class BaseDialogHelper {

    abstract val dialogView: View
    abstract val builder: AlertDialog.Builder

    open var isCancelable: Boolean = true
    open var isTransparent: Boolean = true

    open var dialog: AlertDialog? = null

    open fun create(): AlertDialog {
        dialog = builder
                .setCancelable(isCancelable)
                .create()

        if (isTransparent)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog!!
    }

    open fun onCancelListener(func: () -> Unit): AlertDialog.Builder? =
            builder.setOnCancelListener {
                func()
            }


}