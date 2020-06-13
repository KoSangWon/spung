package com.example.spung

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.activity_analysis_daily.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import java.lang.Thread.sleep
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AnalysisDailyActivity : AppCompatActivity() {
    private val graph1 = floatArrayOf(113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f)
    private val graph2 = floatArrayOf(0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f, 20000f, 12000f, 124400f, 160000f)
    private val legendArr = arrayOf("05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31")

    var day:Array<String> = arrayOf("(월)", "(화)", "(수)", "(목)", "(금)", "(토)", "(일)")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_daily)
        setActionBar()
        showChart()
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

    private fun showChart(){
        val firstChartEntity = ChartEntity(Color.YELLOW, graph1)
        val secondChartEntity = ChartEntity(Color.WHITE, graph2)

        val list = ArrayList<ChartEntity>()
        list.add(firstChartEntity)
//        list.add(secondChartEntity)
        lineChart.legendArray = legendArr
        lineChart.setList(list)
    }

    private fun getCurrentTime(): String{
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
        val formatter2 = DateTimeFormatter.ofPattern(" HH:mm")
        var current = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
        var formatted = current.format(formatter)
        var formatted2 = current.format(formatter2)
        return formatted + day[LocalDateTime.now().dayOfWeek.value - 1] + formatted2
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

        title_text.text = "Daily Analysis"
    }

}
