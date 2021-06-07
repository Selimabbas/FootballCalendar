package com.project.selim.footcalendar.competitions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.selim.footcalendar.R
import com.project.selim.footcalendar.data.models.Competition
import com.project.selim.footcalendar.utils.StringUtils
import kotlinx.android.synthetic.main.competition_list_item.view.*

class CompetitionAdapter(private val items: ArrayList<Competition>,
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