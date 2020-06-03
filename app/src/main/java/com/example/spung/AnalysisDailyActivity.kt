package com.example.spung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_analysis_daily.*

class AnalysisDailyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_daily)
        init()
    }

    private fun init() {
        morebtn.setOnClickListener {
            var i = Intent(this, AnalysisMonthlyActivity::class.java)
            startActivity(i)
        }
    }
}
