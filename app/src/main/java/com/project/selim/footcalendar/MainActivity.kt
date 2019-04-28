package com.project.selim.footcalendar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.project.selim.footcalendar.competitions.CompetitionActivity
import com.project.selim.footcalendar.matchescalendar.CalendarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setUpButtons()
    }

    private fun setUpButtons() {
        update.setOnClickListener {
            startMyActivity(CalendarActivity::class.java)
        }

        competitions.setOnClickListener {
            startMyActivity(CompetitionActivity::class.java)
        }

    }

    private fun <T> startMyActivity(activity: Class<T>) {
        startActivity(Intent(this, activity))
    }

}
