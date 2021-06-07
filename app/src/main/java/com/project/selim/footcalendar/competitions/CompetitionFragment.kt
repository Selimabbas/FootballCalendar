package com.project.selim.footcalendar.competitions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.selim.footcalendar.R
import com.project.selim.footcalendar.data.models.Competition
import kotlinx.android.synthetic.main.competition_layout.*

class CompetitionFragment : Fragment() {
    private val competitionViewModel: CompetitionsViewModel by viewModel()

    private val competitions: ArrayList<Competition> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.competition_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCompetitionListener()
        initRecyclerView()
        competitionViewModel.loadCompetitions()
    }

    private fun initCompetitionListener() {
        competitionViewModel.competitions.observe(viewLifecycleOwner) {
            competitions.clear()
            it?.competitions?.let { it1 ->
                competitions.addAll(it1)
                competition_list.adapter?.notifyDataSetChanged()
            }
        }

        competitionViewModel.errorEvent.observe(viewLifecycleOwner) { error ->
            error?.getContentIfNotHandled()?.let { message -> showSnackbar(message) }
        }

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