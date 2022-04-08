package com.azamat.imagesearchapp.ui.gallery

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.azamat.imagesearchapp.data.UnsplashRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: UnsplashRepository,
    @Assisted state: SavedStateHandle
): ViewModel(){

//    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photo = currentQuery.switchMap { queryString ->
        if (queryString.isNotEmpty()){
            repository.getSearchResults(queryString).cachedIn(viewModelScope)
        }else{
            repository.getSearchResults(queryString)
        }
    }

    fun searchPhotos(query:String) {
        currentQuery.value = query
    }

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "cats"
    }
}