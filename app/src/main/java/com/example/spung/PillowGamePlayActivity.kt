package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class PillowGamePlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pillow_game_play)
        setActionBar()
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

        title_text.text = "Pillow Game Play"
    }
}
