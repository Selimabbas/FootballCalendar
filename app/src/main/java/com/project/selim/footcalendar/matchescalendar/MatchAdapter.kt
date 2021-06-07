package com.project.selim.footcalendar.matchescalendar

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.project.selim.footcalendar.R
import com.project.selim.footcalendar.data.models.Match
import kotlinx.android.synthetic.main.match_list_item.view.*

/**
 * MatchAdapter
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 17/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class MatchAdapter(private val items : ArrayList<Match>,
                   private val context: Context) :
        RecyclerView.Adapter<MatchViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list_item,
                parent, false))
    }

    override fun onBindViewHolder(holderMatch: MatchViewHolder, position: Int) {
        holderMatch.homeTeam?.text = items[position].homeTeam.name
        holderMatch.awayTeam?.text = items[position].awayTeam.name
    }
}

class MatchViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val homeTeam: TextView? = view.home_team
    val awayTeam: TextView? = view.away_team
}