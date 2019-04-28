package com.project.selim.footcalendar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.project.selim.footcalendar.utils.StringUtils
import kotlinx.android.synthetic.main.competition_list_item.view.*

/**
 * CompetitionAdapter
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 20/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class CompetitionAdapter(private val items: ArrayList<CompetitionsRequestModel.Competition>,
                         private val context: Context) :
        RecyclerView.Adapter<CompetitionViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        return CompetitionViewHolder(LayoutInflater.from(context).inflate(R.layout.competition_list_item,
                parent, false))
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.name?.text = StringUtils.appendStrings(items[position].name, "(",
                items[position].area.name, ")")
    }
}

class CompetitionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView? = view.name
}