package com.example.myapplication.utils

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.myapplication.R


class ToolbarUtils {
    fun setupBasicToolbar(toolbar: Toolbar, onFinish: Runnable,context: Context) {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.navigationContentDescription = "Back"
        toolbar.setNavigationOnClickListener(View.OnClickListener { onFinish.run() })
    }
}