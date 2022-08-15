package com.example.android.ui.dialog

import android.content.Context
import com.example.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog

open class BaseBottomSheetDialog(context: Context, layoutRes: Int) :
    BottomSheetDialog(context, R.style.BottomSheetDialog) {

    init {
        this.setContentView(layoutRes)
    }
}