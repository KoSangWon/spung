package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_rhythm_game.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class RhythmGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rhythm_game)
        setActionBar()
        init()
    }

    private fun init() {
        rhythmstartbtn.setOnClickListener {
            var i = Intent(this, RhythmGamePlayActivity::class.java)
            startActivity(i)
        }

        rhythmrecordbtn.setOnClickListener {
            var i = Intent(this, RhythmGameRecordActivity::class.java)
            startActivity(i)
        }

        val values = arrayOf("Memories", "Rolling in the Deep", "This Love", "Sugar")
        song_picker.minValue = 0
        song_picker.maxValue = values.size - 1

        song_picker.displayedValues = values
    }

    private fun setActionBar(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        val view = supportActionBar!!.customView
        val homeImageButton = view.findViewById<ImageButton>(R.id.ic_home)
        homeImageButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        val questionImageButton = view.findViewById<ImageButton>(R.id.question)
        questionImageButton.setOnClickListener {
            val questionIntent = Intent(this, MainActivity::class.java)
            startActivity(questionIntent)
        }

        title_text.text = "Rhythm Game"
    }
}
