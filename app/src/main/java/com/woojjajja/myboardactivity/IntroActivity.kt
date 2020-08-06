package com.woojjajja.myboardactivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.woojjajja.myboardactivity.ui.ListActivity

class IntroActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_intro)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this,ListActivity::class.java))
            finish()
        },1000)
    }
}