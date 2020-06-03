package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_game.*

class SelectGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_game)
        init()
    }

    private fun init() {
        rhythmgamebtn.setOnClickListener {
            var i = Intent(this, RhythmGameActivity::class.java)
            startActivity(i)
        }
        pillowgamebtn.setOnClickListener {
            var i = Intent(this, PillowGameActivity::class.java)
            startActivity(i)
        }
        molegamebtn.setOnClickListener {
            var i = Intent(this, MoleGameActivity::class.java)
            startActivity(i)
        }
    }
}
