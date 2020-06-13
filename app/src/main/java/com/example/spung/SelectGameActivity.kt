package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_select_game.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class SelectGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_game)
        setActionBar()
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

        title_text.text = "Game Select"
    }
}
