package com.tooldev.wabiPlay.content.contentView.contentAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import com.tooldev.wabiPlay.R
import com.tooldev.wabiPlay.data.model.response.Content
import com.tooldev.wabiPlay.data.model.response.ContentResult
import com.tooldev.wabiPlay.data.model.response.Title

class ContentRecyclerAdapter(private val content: Content, private val itemSelected: (ContentResult) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeViewHolder).bind(content.contentResults[position], itemSelected)
    }

    override fun getItemCount(): Int {
        return content.contentResults.size
    }

    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(content: ContentResult, itemSelected: (ContentResult) -> Unit){

            val imageViewPoster = itemView.findViewById<AppCompatImageView>(R.id.imageview_poster)
            val materialTextViewTitle = itemView.findViewById<MaterialTextView>(R.id.materialtexview_title)
            val materialTextViewYear = itemView.findViewById<MaterialTextView>(R.id.materialtexview_year)
            val materialTextViewType = itemView.findViewById<MaterialTextView>(R.id.materialtexview_content_type)
            val constraintLayoutContentItem = itemView.findViewById<ConstraintLayout>(R.id.constraintlayout_content_item)

            Picasso.get().load(content.poster).into(imageViewPoster)
            materialTextViewTitle.text = content.title
            materialTextViewYear.text = content.year
            materialTextViewType.text = content.type
            constraintLayoutContentItem.setOnClickListener {
                itemSelected.invoke(content)
            }
        }
    }
}