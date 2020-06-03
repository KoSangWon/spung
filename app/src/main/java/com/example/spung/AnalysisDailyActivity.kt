package com.example.spung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class AnalysisDailyActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_daily)
    }
}
