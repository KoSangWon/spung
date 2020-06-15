package com.example.spung

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.activity_analysis_weekly.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import kotlin.collections.ArrayList

class AnalysisWeeklyActivity : AppCompatActivity() {
    private val graph1 = floatArrayOf(113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f)
    private val graph2 = floatArrayOf(0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f)
    //    private val legendArr = arrayOf("05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31")
    private lateinit var dateTime:DateTime
    private lateinit var today: String

    var dayName:Array<String> = arrayOf("(월)", "(화)", "(수)", "(목)", "(금)", "(토)", "(일)")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_daily)
        setActionBar()
        init()
    }

    private fun init() {
        JodaTimeAndroid.init(this)
        DateTimeZone.setDefault(DateTimeZone.forID("Asia/Seoul"))
//        morebtn.setOnClickListener {
//            var i = Intent(this, AnalysisMoreActivity::class.java)
//            startActivity(i)
//        }

        Thread(object : Runnable {
            override fun run() {
                while (!Thread.interrupted()) {
                    Thread.sleep(1000)
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            showChart()
                        }
                    })
                }
            }
        }).start()
    }

    private fun showChart(){
        val secondChartEntity = ChartEntity(Color.WHITE, graph2)
        val list = ArrayList<ChartEntity>()
        dateTime = DateTime()
        var day = dateTime.dayOfWeek().get()
        var legendArr = Array<String>(7){i->dayName[day++]}
        list.add(secondChartEntity)
//        list.add(secondChartEntity)
        lineChartWeekly.legendArray = legendArr
        lineChartWeekly.setList(list)
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
            val questionIntent = Intent(this, AnalysisDailyHelpActivity::class.java)
            startActivity(questionIntent)
        }

        title_text.text = "Weekly Analysis"
    }

}
