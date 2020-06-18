package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_rhythm_game.*
import kotlinx.android.synthetic.main.activity_sound_effect.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class SoundEffectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_effect)
        setActionBar()
        init()
    }

    private fun init(){
        val values = arrayOf("Sound 1", "Sound 2", "Sound 3", "Sound 4")
        sound_picker.minValue = 0
        sound_picker.maxValue = values.size - 1

        sound_picker.displayedValues = values
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

        title_text.text = "Sound Effect"

    }
}
