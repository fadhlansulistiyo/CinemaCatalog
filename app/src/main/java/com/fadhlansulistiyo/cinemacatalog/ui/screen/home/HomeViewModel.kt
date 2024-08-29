package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.MovieUseCase
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.PeopleUseCase
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCase: MovieUseCase,
    private val tvUseCase: TvUseCase,
    private val peopleUseCase: PeopleUseCase
) : ViewModel() {

    val nowPlayingMovies: Flow<PagingData<Movie>> = movieUseCase.getNowPlaying()
        .cachedIn(viewModelScope)

    private val _topRatedTv = MutableStateFlow<Resource<List<Tv>>>(Resource.Loading())
    val topRatedTv: StateFlow<Resource<List<Tv>>> get() = _topRatedTv

    private val _trendingPeople = MutableStateFlow<Resource<List<People>>>(Resource.Loading())
    val trendingPeople: StateFlow<Resource<List<People>>> get() = _trendingPeople

    init {
        fetchTopRatedTvShows()
        fetchTrendingPeople()
    }

    fun retryData() {
        fetchTopRatedTvShows()
        fetchTrendingPeople()

    }

    private fun fetchTopRatedTvShows() {
        viewModelScope.launch {
            tvUseCase.getTopRatedTv()
                .collect { resource ->
                    _topRatedTv.value = resource
                }
        }
    }

    private fun fetchTrendingPeople() {
        viewModelScope.launch {
            peopleUseCase.getTrendingPeople()
                .collect { resource ->
                    _trendingPeople.value = resource
                }
        }
    }
}