package com.project.selim.footcalendar.competitions

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.selim.footcalendar.data.models.CompetitionsRequestModel
import com.project.selim.footcalendar.R
import kotlinx.android.synthetic.main.competition_layout.*

class CompetitionFragment : Fragment() {
    private val competitionViewModel
            by lazy { ViewModelProviders.of(this).get(CompetitionsViewModel::class.java) }

    private val competitions: ArrayList<CompetitionsRequestModel.Competition> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.competition_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCompetitionListener()
        initRecyclerView()
        competitionViewModel.updateCompetitions()
    }

    private fun initCompetitionListener() {
        competitionViewModel.competitions.observe(this, Observer {
            competitions.clear()
            it?.competitions?.let { it1 ->
                competitions.addAll(it1)
                competition_list.adapter?.notifyDataSetChanged()
            }
        })

        competitionViewModel.errorEvent.observe(this, Observer { error ->
            error?.getContentIfNotHandled()?.let { message -> showSnackbar(message) }
        })

    }

    private fun initRecyclerView() {
        context?.let {
            competition_list.layoutManager = LinearLayoutManager(it)
            competition_list.adapter = CompetitionAdapter(competitions, it)
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