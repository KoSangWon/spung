package com.example.spung

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setActionBar()
        init()
    }

    private fun setActionBar(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        val view = supportActionBar!!.customView
        val homeImageButton = view.findViewById<ImageButton>(R.id.ic_home)
        homeImageButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }

        val questionImageButton = view.findViewById<ImageButton>(R.id.question)
        questionImageButton.setOnClickListener {
            val questionIntent = Intent(this, MainActivity::class.java)
            startActivity(questionIntent)
        }
    }

    private fun init(){
        profileBtn.setOnClickListener {
            val nextIntent = Intent(this, ProfileActivity::class.java)
            startActivity(nextIntent)
        }

        soundBtn.setOnClickListener {
            val nextIntent = Intent(this, SoundEffectActivity::class.java)
            startActivity(nextIntent)
        }

        lightBtn.setOnClickListener {
            val nextIntent = Intent(this, LightActivity::class.java)
            startActivity(nextIntent)
        }

        initBtn.setOnClickListener {
            //초기화 기능 구현
        }
    }


}
