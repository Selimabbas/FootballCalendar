package com.project.selim.footcalendar.matchescalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.selim.footcalendar.R
import com.project.selim.footcalendar.data.models.Match
import kotlinx.android.synthetic.main.matches_calendar_layout.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * CalendarActivity
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 19/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */

class CalendarFragment : Fragment() {

    private val calendarViewModel: CalendarViewModel by viewModel()

    private val matches: ArrayList<Match> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_calendar_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMatchesListener()
        initRecyclerView()
        calendarViewModel.callNetwork()
    }

    private fun initMatchesListener() {
        calendarViewModel.matches.observe(viewLifecycleOwner) {
            matches.clear()
            it?.matches?.let { it1 ->
                matches.addAll(it1)
                match_list.adapter?.notifyDataSetChanged()
            }
        }

        calendarViewModel.errorEvent.observe(viewLifecycleOwner) { error ->
            error?.getContentIfNotHandled()?.let { message -> showSnackbar(message) }
        }

    }

    private fun initRecyclerView() {
        context?.let {
            val linearLayoutManager = LinearLayoutManager(it)
            match_list.layoutManager = linearLayoutManager
            match_list.adapter = MatchAdapter(matches, it)
            val dividerItemDecoration = DividerItemDecoration(
                match_list.context,
                linearLayoutManager.orientation
            )
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