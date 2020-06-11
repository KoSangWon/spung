package com.example.spung

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothClass
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothActivity : AppCompatActivity() {
    lateinit var bt:BluetoothSPP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        bt = BluetoothSPP(this)
        if(!bt.isBluetoothAvailable){
            Toast.makeText(this, "can't use bluetooth", Toast.LENGTH_SHORT).show()
            finish()
        }

        bt.setOnDataReceivedListener { data, message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        bt.setBluetoothConnectionListener(object:BluetoothSPP.BluetoothConnectionListener{
            override fun onDeviceDisconnected() {
                Toast.makeText(applicationContext, "1", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnected(name: String?, address: String?) {
                Toast.makeText(applicationContext, "2", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnectionFailed() {
                Toast.makeText(applicationContext, "3", Toast.LENGTH_SHORT).show()
            }
        })
        btnConnect.setOnClickListener {
            if(bt.serviceState == BluetoothState.STATE_CONNECTED){
                bt.disconnect()
            }
            else{
                var i = Intent(this, BluetoothClass.Device::class.java)
                startActivityForResult(intent,BluetoothState.REQUEST_CONNECT_DEVICE)
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
            var i = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(i, BluetoothState.REQUEST_ENABLE_BT)
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
        btnSend.setOnClickListener {
            bt.send("Text", true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE){
            if(requestCode == Activity.RESULT_OK)
                bt.connect(data)
        }
        else if(requestCode == BluetoothState.REQUEST_ENABLE_BT){
            if(requestCode == Activity.RESULT_OK){
                bt.setupService()
                bt.startService(BluetoothState.DEVICE_OTHER)
                setup()
            }
            else{
                Toast.makeText(this,"no",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}