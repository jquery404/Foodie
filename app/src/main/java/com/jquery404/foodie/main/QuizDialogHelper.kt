package com.jquery404.foodie.main

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.jquery404.foodie.R

class QuizDialogHelper(context: Context) : BaseDialogHelper() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.layout_quiz_dialog, null)
    }

    override val builder: AlertDialog.Builder =
            AlertDialog.Builder(context).setView(dialogView)

    var rightAnswer: Int = 0

    val quizTitle: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.tvQuestion)
    }

    val optionA:RadioButton by lazy {
        dialogView.findViewById<RadioButton>(R.id.rbOptionA)
    }

    val optionB:RadioButton by lazy {
        dialogView.findViewById<RadioButton>(R.id.rbOptionB)
    }

    val radioGroup: RadioGroup by lazy {
        dialogView.findViewById<RadioGroup>(R.id.rgOptions)
    }

    private val doneIcon: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.ivDone)
    }

    private val closeIcon: ImageView by lazy {
        dialogView.findViewById<ImageView>(R.id.ivClose)
    }

    fun closeIconClickListener(func: (() -> Unit)? = null) =
            with(closeIcon) {
                setClickListenerToDialogIcon(func)
            }

    fun doneIconClickListener(func: (() -> Unit)? = null) =
            with(doneIcon) {
                setClickListenerToDialogIcon(func)
            }

    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }

    }
}