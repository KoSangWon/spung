package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        init()
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
