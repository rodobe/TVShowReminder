package com.tooldev.test.view.fragments

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tooldev.test.R
import com.tooldev.test.databinding.FragmentArticlesBinding
import com.tooldev.test.data.model.response.Hit
import com.tooldev.test.util.inTransaction
import com.tooldev.test.view.adapters.ArticlesRecyclerAdapter
import com.tooldev.test.view.viewModels.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


@AndroidEntryPoint
class ArticlesHomeFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener{

    private val viewModel: ArticlesViewModel by viewModels()
    private val compositeSubscription = CompositeDisposable()
    private var adapter: ArticlesRecyclerAdapter? = null
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
    private lateinit var binding: FragmentArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding. lifecycleOwner= viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setToolbar()
        setListenerSubjects()
        setRefreshListener()
    }

    private fun setToolbar(){
        binding.toolbarArticlesHome
    }

    private fun setListenerSubjects(){
        compositeSubscription.add(viewModel.articlesSubject.subscribe({ value ->
            updateArticlesAdapter(value)
        }, { error ->
            onError(error)
        }))
    }

    private fun updateArticlesAdapter(hits: List<Hit>){
        binding.recyclerviewArticles.removeAllViews()
        val mutableHits = hits.toMutableList()
        if (adapter == null){
            adapter = ArticlesRecyclerAdapter(mutableHits) { url -> showArticle(url) }
            binding.recyclerviewArticles.layoutManager = LinearLayoutManager(context)
            binding.recyclerviewArticles.adapter = adapter
        } else{
            adapter?.setNewData(mutableHits)
            adapter?.notifyDataSetChanged()
        }
        binding.swipeRefreshLayoutArticleshome.isRefreshing = false
        setTouchListener(adapter, mutableHits)
    }

    private fun onError(throwable: Throwable){

    }

    private  fun setTouchListener(adapter: ArticlesRecyclerAdapter?, hits: MutableList<Hit>) {

        val itemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean { return true }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val isCanceled = dX == 0f && !isCurrentlyActive

                    if (isCanceled) { c.drawRect(itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat(), clearPaint)
                        super.onChildDraw(c, recyclerView, viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                        )
                        return
                    }

                    val drawable = ContextCompat.getDrawable(context!!, R.drawable.ic_delete)
                    drawable?.bounds = Rect(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    drawable?.draw(c)
                }
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteArticles(hits[position])
                hits.removeAt(position)
                adapter?.setNewData(hits)
                adapter?.notifyDataSetChanged()
            }


        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewArticles)

    }

    private fun setRefreshListener(){
        binding.swipeRefreshLayoutArticleshome.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.getArticles()
    }

    private fun showArticle(url: String){
        (activity as FragmentActivity).supportFragmentManager.inTransaction {
            add(R.id.contentFrame, WebViewArticlesFragment.getInstance(url))
        }
    }

}