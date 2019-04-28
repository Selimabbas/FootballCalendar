package com.project.selim.footcalendar.matchescalendar

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.project.selim.footcalendar.MatchAdapter
import com.project.selim.footcalendar.R
import com.project.selim.footcalendar.MatchRequestModel
import kotlinx.android.synthetic.main.matches_calendar_layout.*

/**
 * CalendarActivity
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 19/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

class CalendarActivity : AppCompatActivity() {

    private val footViewModel
            by lazy { ViewModelProviders.of(this).get(CalendarViewModel::class.java) }

    private val matches: ArrayList<MatchRequestModel.Match> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.matches_calendar_layout)

        initMatchesListener()
        initRecyclerView()
        footViewModel.callNetwork()
    }

    private fun initMatchesListener() {
        footViewModel.matches.observe(this, Observer {
            matches.clear()
            it?.matches?.let {
                it1 -> matches.addAll(it1)
                match_list.adapter.notifyDataSetChanged()
            }
        })

        footViewModel.errorEvent.observe(this, Observer { error ->
            error?.getContentIfNotHandled()?.let { message -> showSnackbar(message) }
        })

    }

    private fun initRecyclerView() {
        match_list.layoutManager = LinearLayoutManager(this)
        match_list.adapter = MatchAdapter(matches, this)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
                root_layout, // Parent view
                message, // Message to show
                Snackbar.LENGTH_SHORT // How long to display the message.
        ).show()
    }
}