package com.example.spung

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.DeviceList
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mole_game_play.*
import kotlinx.android.synthetic.main.activity_mood_light.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import kotlin.random.Random

class MoleGamePlayActivity : AppCompatActivity() {
    lateinit var bt: BluetoothSPP
    lateinit var sharedPreferences: SharedPreferences
    var check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mole_game_play)
        setActionBar()
        init()
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

        title_text.text = "Mole Game Play"
    }

    private fun init() {
        bt = BluetoothSPP(this)
        if(!bt.isBluetoothAvailable){
            Toast.makeText(this, "can't use bluetooth", Toast.LENGTH_SHORT).show()
            finish()
        }

        bt.setOnDataReceivedListener { Data, message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }

        bt.setBluetoothConnectionListener(object:BluetoothSPP.BluetoothConnectionListener{
            override fun onDeviceDisconnected() {
                Toast.makeText(applicationContext, "Bluetooth is disconnected", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnected(name: String?, address: String?) {
                Toast.makeText(applicationContext, "Bluetooth is connected", Toast.LENGTH_SHORT).show()
                var myTimer = MyTimer(3000, 1000)
                myTimer.start()
            }

            override fun onDeviceConnectionFailed() {
                Toast.makeText(applicationContext, "Bluetooth connection is failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if(!bt.isBluetoothEnabled){
            bt.enable()
        }
        else{
            if(!bt.isServiceAvailable){
                bt.setupService()
                bt.startService(BluetoothState.DEVICE_OTHER)
                setup()
            }
        }
    }

    fun setup(){
        var i = Intent(applicationContext, DeviceList::class.java)
        startActivityForResult(i, BluetoothState.REQUEST_CONNECT_DEVICE)
    }

    fun game() {
        Log.d("test", "test")
        for (i in 1..100) {
            bt.send("0", true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bt.send("8", true)
        bt.stopService()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE){
            if(resultCode == Activity.RESULT_OK){
                bt.connect(data)
            }
        }
        else if(requestCode == BluetoothState.REQUEST_ENABLE_BT){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this,"yes",Toast.LENGTH_SHORT).show()
                bt.setupService()
            }
            else{
                Toast.makeText(this,"no",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    inner class MyTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture,
        countDownInterval
    ) {

        override fun onFinish() {
            countDown.visibility = View.GONE
            var myTimer2 = MyTimer2(60000, 500)
            bt.send("7", true)
            myTimer2.start()
        }

        override fun onTick(p0: Long) {
            countDown?.setText(((p0 - 1)/1000 + 1).toString())
            Log.d("test", p0.toString())
        }
    }

    inner class MyTimer2(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture,
        countDownInterval
    ) {

        override fun onFinish() {
            countDown.visibility = View.GONE
            bt.send("8", true)
        }

        override fun onTick(p0: Long) {
            if(!check){
                bt.send(Random.nextInt(4).toString(), true)
                check = true
            }
            else{
                bt.send("6", true)
                check = false
            }
        }
    }
}
