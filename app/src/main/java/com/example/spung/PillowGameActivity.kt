package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pillow_game.*

class PillowGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pillow_game)
        init()
    }
    private fun init() {
        pillowstartbtn.setOnClickListener {
            var i = Intent(this, PillowGamePlayActivity::class.java)
            startActivity(i)
        }
        pillowrecordbtn.setOnClickListener {
            var i = Intent(this, PillowGameRecordActivity::class.java)
            startActivity(i)
        }
    }
}
