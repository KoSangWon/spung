package com.example.spung

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mole_game_record.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoleGameRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mole_game_record)
        setActionBar()

//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://localhost:5000")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var server = retrofit.create(RecordService::class.java)
//
//        button_get.setOnClickListener {
//
//            server.getRequest("records").enqueue(object:Callback<ResponseRecords>{
//                override fun onFailure(call: Call<ResponseRecords>?, t: Throwable?) {
//
//                }
//
//                override fun onResponse(call: Call<ResponseRecords>?, response: Response<ResponseRecords>?) {
//                    println(response?.body().toString())
//                }
//
//            })
//        }
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

        title_text.text = "Mole Game Record"
    }
}
