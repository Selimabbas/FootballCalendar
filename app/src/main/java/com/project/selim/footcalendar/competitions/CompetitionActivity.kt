package com.project.selim.footcalendar.competitions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.project.selim.footcalendar.CompetitionAdapter
import com.project.selim.footcalendar.CompetitionsRequestModel
import com.project.selim.footcalendar.R
import kotlinx.android.synthetic.main.competition_layout.*

/**
 * CompetitionActivity
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 20/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class CompetitionActivity : AppCompatActivity() {
    private val competitionViewModel
            by lazy { ViewModelProviders.of(this).get(CompetitionsViewModel::class.java) }

    private val competitions: ArrayList<CompetitionsRequestModel.Competition> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.competition_layout)

        initCompetitionListener()
        initRecyclerView()
        competitionViewModel.updateCompetitions()
    }

    private fun initCompetitionListener() {
        competitionViewModel.competitions.observe(this, Observer {
            competitions.clear()
            it?.competitions?.let { it1 ->
                competitions.addAll(it1)
                competition_list.adapter.notifyDataSetChanged()
            }
        })

        competitionViewModel.errorEvent.observe(this, Observer { error ->
            error?.getContentIfNotHandled()?.let { message -> showSnackbar(message) }
/*
            if (error != null) {
                String content = error.getContentIfNotHandled();
                if (content != null) {
                    showSnackbar(content);
                }
            }
*/

        })

    }

    private fun initRecyclerView() {
        competition_list.layoutManager = LinearLayoutManager(this)
        competition_list.adapter = CompetitionAdapter(competitions, this)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
                root_layout, // Parent view
                message, // Message to show
                Snackbar.LENGTH_SHORT // How long to display the message.
        ).show()
    }
}