package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rhythm_game.*

class RhythmGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rhythm_game)
        init()
    }

    private fun init() {
        rhythmstratbtn.setOnClickListener {
            var i = Intent(this, RhythmGamePlayActivity::class.java)
            startActivity(i)
        }
        rhythmrecordbtn.setOnClickListener {
            var i = Intent(this, RhythmGameRecordActivity::class.java)
            startActivity(i)
        }
    }
}
