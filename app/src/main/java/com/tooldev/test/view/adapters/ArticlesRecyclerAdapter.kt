package com.tooldev.test.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.tooldev.test.R
import com.tooldev.test.data.model.response.Hit
import java.text.SimpleDateFormat
import java.util.*

class ArticlesRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var hits: MutableList<Hit>
    lateinit var urlReturn: (String) -> Unit

    constructor(hits: MutableList<Hit>, urlReturn: (String) -> Unit): this(){
        this.urlReturn = urlReturn
        this.hits = hits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(getLayout(viewType), parent, false)
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder  as ArticlesViewHolder).bind(hits[position], urlReturn)
    }

    override fun getItemCount(): Int {
        return hits.size
    }

    class ArticlesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(hit: Hit?, urlReturn: (String) -> Unit){
            if (hit != null) {
                val content = itemView.findViewById<ConstraintLayout>(R.id.constraintlayout_articleitem)
                val title = itemView.findViewById<MaterialTextView>(R.id.materialtexview_title)
                val timeStamp = itemView.findViewById<MaterialTextView>(R.id.materialtexview_timestamp)
                val author = itemView.findViewById<MaterialTextView>(R.id.materialtexview_author)

                content.setOnClickListener {
                    onClickListener(urlReturn, hit.story_url)
                }
                title.text = getTitle(hit)
                timeStamp.text = getDate(hit.created_at)
                author.text = hit.author
            }
        }

        private fun getTitle(hit: Hit): String?{
            if (hit.story_title != null){
                return hit.story_title
            }else{
                return hit.title
            }
        }

        @SuppressLint("SimpleDateFormat")
        private fun getDate(createdAt: String?): String{
            val formatter = "dd-MM-yyyy"
            val dateFormat = SimpleDateFormat(formatter)
            if (createdAt != null){
                return dateFormat.format(Date())
            } else{
                return "No date"
            }
        }

        private fun onClickListener(urlReturn: (String) -> Unit, url: String?){
            if (url != null) {
                urlReturn.invoke(url)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private fun getLayout(viewType: Int): Int{
        return R.layout.articles_item_view
    }

    fun setNewData(hits: MutableList<Hit>){
        this.hits = hits
    }

}