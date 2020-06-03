package com.example.spung

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_analysis_daily.*
import java.lang.Thread.sleep
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AnalysisDailyActivity : AppCompatActivity() {
    var day:Array<String> = arrayOf("(월)", "(화)", "(수)", "(목)", "(금)", "(토)", "(일)")

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

        Thread(object : Runnable {
            override fun run() {
                while (!Thread.interrupted()) {
                    Thread.sleep(1000)
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            timetext.setText(getCurrentTime())
                        }
                    })
                }
            }
        }).start()
    }

    private fun getCurrentTime(): String{
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
        val formatter2 = DateTimeFormatter.ofPattern(" HH:mm")
        var current = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
        var formatted = current.format(formatter)
        var formatted2 = current.format(formatter2)
        return formatted + day[LocalDateTime.now().dayOfWeek.value - 1] + formatted2
    }
}
