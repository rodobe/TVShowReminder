package com.tooldev.test.view.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tooldev.test.dao.DeletedHit
import com.tooldev.test.data.api.Result
import com.tooldev.test.data.model.response.Hit
import com.tooldev.test.data.repository.ArticlesRepository
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch


class ArticlesViewModel @ViewModelInject constructor(
        private val articlesRepository: ArticlesRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val articlesSubject: PublishSubject<List<Hit>> = PublishSubject.create()
    var hits: List<Hit>? = null

    init {
        getArticles()
    }

    fun getArticles(){
        viewModelScope.launch {
                articlesRepository.getArticles().let { result ->
                    if (result is Result.Success) {
                        sendArticles(result.data)
                    }
                }
        }
    }

    fun deleteArticles(hit: Hit){
        viewModelScope.launch{
                articlesRepository.deleteArticle(DeletedHit(hit.author, hit.story_id))
        }
    }

    private fun sendArticles(hits: List<Hit>){
        articlesSubject.onNext(hits)
    }

}