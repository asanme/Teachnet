package com.asanme.teachnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        super.getActionBar()?.hide()

        var frag:FunctionBarFragment = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()
    }
}