package com.tooldev.wabiPlay.content.contentView.contentViewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tooldev.wabiPlay.content.contentView.contentFragments.ContentDetailFragment
import com.tooldev.wabiPlay.data.api.Result
import com.tooldev.wabiPlay.data.model.response.Title
import com.tooldev.wabiPlay.data.repository.ContentRepository
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch

class ContentDetailViewModel @ViewModelInject constructor(private val contentRepository: ContentRepository): ViewModel(){

    val resultPublishSubject: PublishSubject<Title> = PublishSubject.create()

    fun start(){
        getContent()
    }

    private fun getContent(){
        viewModelScope.launch {
            contentRepository.getTitle(ContentDetailFragment.title, ContentDetailFragment.type, ContentDetailFragment.plot).let { result ->
                if (result is Result.Success) {
                    setSearchResult(result.data)
                }
            }
        }
    }

    private fun setSearchResult(title: Title){
        resultPublishSubject.onNext(title)
    }

}