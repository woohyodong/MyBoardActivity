package com.woojjajja.myboardactivity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutID(): Int

    abstract fun getAppTitle(): String

    abstract fun setUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        getAppTitle()?.let {
            title = it
        }
        setUp()
    }

}