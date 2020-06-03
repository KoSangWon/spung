package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mole_game.*

class MoleGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mole_game)
        init()
    }
    private fun init() {
        molestartbtn.setOnClickListener {
            var i = Intent(this, MoleGamePlayActivity::class.java)
            startActivity(i)
        }
        molerecordbtn.setOnClickListener {
            var i = Intent(this, MoleGameRecordActivity::class.java)
            startActivity(i)
        }
    }
}
