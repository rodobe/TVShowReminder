package com.tooldev.wabiPlay.content.contentView.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.tooldev.wabiPlay.R
import com.tooldev.wabiPlay.content.contentView.contentAdapters.ContentRecyclerAdapter
import com.tooldev.wabiPlay.content.contentView.contentViewModels.ContentViewModel
import com.tooldev.wabiPlay.data.model.response.Content
import com.tooldev.wabiPlay.data.model.response.ContentResult
import com.tooldev.wabiPlay.databinding.FragmentContentBinding
import com.tooldev.wabiPlay.util.inTransactionAdd
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class ContentFragment: Fragment() {

    private val viewModel: ContentViewModel by viewModels()
    private val compositeSubscription = CompositeDisposable()
    private lateinit var binding: FragmentContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setListenerSubjects()
        binding.viewModel?.start()
    }

    private fun setListenerSubjects(){
        compositeSubscription.add(viewModel.contentPublishSubject.subscribe {
            upDateContentList(it)
        })
    }

    private fun upDateContentList(content: Content){
        binding.recyclerviewContentList.adapter = ContentRecyclerAdapter(content) {
            showContentDetail(it)
        }
        (binding.recyclerviewContentList.adapter as ContentRecyclerAdapter).notifyDataSetChanged()
    }

    private fun onError(throwable: Throwable){

    }

    private fun showContentDetail(content: ContentResult){
        (activity as FragmentActivity).supportFragmentManager.inTransactionAdd {
            add(R.id.contentFrame, ContentDetailFragment.getInstance(content))
        }
    }

    companion object{
        var search: String? = null
        var type: String? = null

        fun getInstance(bundle: Bundle): ContentFragment {
            search = bundle.getString("search", "")
            type = bundle.getString("type", "")
            return ContentFragment()
        }
    }

}