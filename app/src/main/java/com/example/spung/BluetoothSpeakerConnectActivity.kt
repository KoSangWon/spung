package com.example.spung

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.DeviceList
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothSpeakerConnectActivity : AppCompatActivity() {

    lateinit var bt: BluetoothSPP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        bt = BluetoothSPP(this)
        if(!bt.isBluetoothAvailable){
            Toast.makeText(this, "can't use bluetooth", Toast.LENGTH_SHORT).show()
            finish()
        }

        bt.setBluetoothConnectionListener(object: BluetoothSPP.BluetoothConnectionListener{
            override fun onDeviceDisconnected() {
                Toast.makeText(applicationContext, "블루투스가 연결에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnected(name: String?, address: String?) {
                Toast.makeText(applicationContext, "블루투스가 연결되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onDeviceConnectionFailed() {
                Toast.makeText(applicationContext, "블루투스가 연결이 해제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        })
        btnConnect.setOnClickListener {
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

    fun setup(){
        btnSend.setOnClickListener {
            bt.send("1", true)
            Toast.makeText(this,"0", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this,"yes", Toast.LENGTH_SHORT).show()
                bt.setupService()
            }
            else{
                Toast.makeText(this,"no", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
