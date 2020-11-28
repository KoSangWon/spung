package com.example.spung

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.DeviceList
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mood_light.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class MoodLightActivity : AppCompatActivity() {
    lateinit var bt: BluetoothSPP
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_light)
        setActionBar()
        init()
    }

    private fun init() {
        bt = BluetoothSPP(this)
        sharedPreferences = getSharedPreferences("Shared", Context.MODE_PRIVATE);
//        var editor: SharedPreferences.Editor = sharedPreferences.edit()
//        var gson = Gson()
//        editor.putString("bt", gson.toJson(bt))
//        editor.apply()

        if(!bt.isBluetoothAvailable){
            Toast.makeText(this, "can't use bluetooth", Toast.LENGTH_SHORT).show()
            finish()
        }

        bt.setOnDataReceivedListener { Data, message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            Log.d("test", bt.toString())
            Log.d("test", bt.isServiceAvailable().toString())
        }

        bt.setBluetoothConnectionListener(object:BluetoothSPP.BluetoothConnectionListener{
            override fun onDeviceDisconnected() {
                Toast.makeText(applicationContext, "Bluetooth is disconnected", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnected(name: String?, address: String?) {
                Toast.makeText(applicationContext, "Bluetooth is connected", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnectionFailed() {
                Toast.makeText(applicationContext, "Bluetooth connection is failed", Toast.LENGTH_SHORT).show()
            }
        })
        moodlightOnOffbtn.setOnClickListener {
            if(bt.serviceState == BluetoothState.STATE_CONNECTED){
                bt.disconnect()
            }
            else{
                var i = Intent(applicationContext, DeviceList::class.java)
                startActivityForResult(i, BluetoothState.REQUEST_CONNECT_DEVICE)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bt.stopService()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE){
            if(resultCode == Activity.RESULT_OK)
                bt.connect(data)
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

    fun setup(){
        var blue = false
        redbtn.setOnClickListener {
            bt.send("1",false)
        }
        bluebtn.setOnClickListener {
            Log.d("test", bt.isServiceAvailable().toString())
            if(blue){
                bt.send("4",true)
                bluebtn.setBackgroundResource(R.drawable.ic_on)
                blue = false
            }
            else {
                bt.send("5", true)
                bluebtn.setBackgroundResource(R.drawable.ic_off)
                blue = true
            }
        }
    }

    private fun setActionBar() {
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
            val questionIntent = Intent(this, MoodLightHelpActivity::class.java)
            startActivity(questionIntent)
        }

        title_text.text = "Mood Light"
    }
}
