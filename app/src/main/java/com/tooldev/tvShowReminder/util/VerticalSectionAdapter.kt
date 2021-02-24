package com.tooldev.tvShowReminder.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.tooldev.tvShowReminder.R
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.home.homeView.adapters.OnAirTvShowAdapter

class VerticalSectionAdapter(private val sectionTitle: String, private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var genresResult: GenresResult? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_vertical_section, parent, false)
        return VerticalSectionViewHolder(view)
    }

    override fun getItemCount(): Int  = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VerticalSectionViewHolder).bind(sectionTitle, adapter, genresResult)
    }

    class VerticalSectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(sectionTitle: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, genresResult: GenresResult?) {
            itemView.findViewById<MaterialTextView>(R.id.materialtextview_section_title).text = sectionTitle
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerview_vertical_section)
            recyclerView.adapter = adapter
        }
    }

    fun showGenres(genresResult: GenresResult){
        this.genresResult =  genresResult
        (adapter as OnAirTvShowAdapter).updateGenres(genresResult)
        adapter.notifyDataSetChanged()
    }
}