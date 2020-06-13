package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.layout., menu)
//        return true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
//
//    private fun hideBar() {
//        supportActionBar?.hide()
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//        WindowManager.LayoutParams.FLAG_FULLSCREEN)
//    }

    private fun init() {
        supportActionBar?.hide()

        analysisbtn.setOnClickListener {
            var i = Intent(this, AnalysisDailyActivity::class.java)
            startActivity(i)
        }
        gamebtn.setOnClickListener {
            var i = Intent(this, SelectGameActivity::class.java)
            startActivity(i)
        }
        moodlightbtn.setOnClickListener {
            var i = Intent(this, MoodLightActivity::class.java)
            startActivity(i)
        }
        speakerbtn.setOnClickListener {
            var i = Intent(this, BluetoothSpeakerActivity::class.java)
            startActivity(i)
        }
        settingbtn.setOnClickListener {
            var i = Intent(this, SettingActivity::class.java)
            startActivity(i)
        }
    }

}
