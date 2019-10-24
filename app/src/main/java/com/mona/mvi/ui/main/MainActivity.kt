package com.mona.mvi.ui.main

import android.os.Bundle
import com.mona.mvi.R
import com.mona.mvi.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
