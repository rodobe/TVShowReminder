package com.tooldev.tvShowReminder.home.homeView.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tooldev.tvShowReminder.R
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import com.tooldev.tvShowReminder.data.model.response.tvShowsDetails.TvShowDetails

class SubscribedTvShowsAdapter(private val tvShowsResult: List<TvShowDetails>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_onsubscribed_item, parent, false)
        return OnSubscribedTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OnSubscribedTvShowViewHolder).bind(tvShowsResult[position])
    }

    override fun getItemCount(): Int {
        return tvShowsResult.size
    }

    class OnSubscribedTvShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(tvShow: TvShowDetails){
            val poster = itemView.findViewById<AppCompatImageView>(R.id.imageview_poster)

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + tvShow.poster_path).into(poster)
        }

    }

}