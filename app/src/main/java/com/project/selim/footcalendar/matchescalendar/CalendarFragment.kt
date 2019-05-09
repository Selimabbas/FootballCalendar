package com.project.selim.footcalendar.matchescalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.selim.footcalendar.data.models.MatchRequestModel
import com.project.selim.footcalendar.R
import kotlinx.android.synthetic.main.matches_calendar_layout.*


/**
 * CalendarActivity
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 19/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

class CalendarFragment : Fragment() {

    private val footViewModel
            by lazy { ViewModelProviders.of(this).get(CalendarViewModel::class.java) }

    private val matches: ArrayList<MatchRequestModel.Match> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.matches_calendar_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMatchesListener()
        initRecyclerView()
        footViewModel.callNetwork()
    }

    private fun initMatchesListener() {
        footViewModel.matches.observe(this, Observer {
            matches.clear()
            it?.matches?.let { it1 ->
                matches.addAll(it1)
                match_list.adapter?.notifyDataSetChanged()
            }
        })

        footViewModel.errorEvent.observe(this, Observer { error ->
            error?.getContentIfNotHandled()?.let { message -> showSnackbar(message) }
        })

    }

    private fun initRecyclerView() {
        context?.let {
            val linearLayoutManager = LinearLayoutManager(it)
            match_list.layoutManager = linearLayoutManager
            match_list.adapter = MatchAdapter(matches, it)
            val dividerItemDecoration = DividerItemDecoration(match_list.context,
                    linearLayoutManager.orientation)
            match_list.addItemDecoration(dividerItemDecoration)
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
                root_layout, // Parent view
                message, // Message to show
                Snackbar.LENGTH_SHORT // How long to display the message.
        ).show()
    }
}