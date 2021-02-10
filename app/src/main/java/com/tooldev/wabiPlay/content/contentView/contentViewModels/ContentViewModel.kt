package com.tooldev.wabiPlay.content.contentView.contentViewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tooldev.wabiPlay.content.contentView.contentFragments.ContentFragment
import com.tooldev.wabiPlay.data.api.Result
import com.tooldev.wabiPlay.data.model.response.Content
import com.tooldev.wabiPlay.data.repository.ContentRepository
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch

class ContentViewModel @ViewModelInject constructor(private val contentRepository: ContentRepository): ViewModel() {

    val contentPublishSubject: PublishSubject<Content> = PublishSubject.create()

    fun start(){
        getContent()
    }

    private fun getContent(){
        viewModelScope.launch {
            contentRepository.getContent(ContentFragment.search, ContentFragment.type).let { result ->
                if (result is Result.Success) {
                    sendMovies(result.data)
                }
            }
        }
    }

    private fun sendMovies(content: Content){
        contentPublishSubject.onNext(content)
    }

}