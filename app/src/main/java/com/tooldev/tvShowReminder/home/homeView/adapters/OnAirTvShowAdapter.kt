package com.tooldev.tvShowReminder.home.homeView.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import com.tooldev.tvShowReminder.R
import com.tooldev.tvShowReminder.data.model.response.home.GenresResult
import com.tooldev.tvShowReminder.data.model.response.home.TvShow
import javax.inject.Inject

class OnAirTvShowAdapter @Inject constructor(private val tvShowsResult: List<TvShow>, private val tvId: (Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var genresResult: GenresResult? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_onair_item, parent, false)
        return OnAirTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OnAirTvShowViewHolder).bind(tvShowsResult[position], genresResult, tvId)
    }

    override fun getItemCount(): Int {
        return tvShowsResult.size
    }

    class OnAirTvShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(tvShow: TvShow, genresResult: GenresResult?, tvId: (Int) -> Unit){
            val title = itemView.findViewById<MaterialTextView>(R.id.materialtextview_name)
            val genreTextView = itemView.findViewById<MaterialTextView>(R.id.materialtexview_genre)
            val poster = itemView.findViewById<AppCompatImageView>(R.id.imageview_poster)
            if (tvShow.genre_ids != null && tvShow.genre_ids.isNotEmpty()) {
                val genre = genresResult?.genres?.find { genre -> tvShow.genre_ids.first() == genre.id }
                genreTextView.text = genre?.name
            }
            title.text = tvShow.name
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + tvShow.poster_path).into(poster)

            poster.setOnClickListener {
                tvId.invoke(tvShow.id!!)
            }
        }
    }

    fun updateGenres(genresResult: GenresResult){
        this.genresResult = genresResult
    }

}