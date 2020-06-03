package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_analysis_monthly.*

class AnalysisMonthlyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_monthly)
        init()
    }

    private fun init() {
        morebtn2.setOnClickListener {
            var i = Intent(this, AnalysisMoreActivity::class.java)
            startActivity(i)
        }
    }
}
