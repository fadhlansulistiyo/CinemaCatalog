package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.MovieUseCase
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_NULL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movieDetails = MutableLiveData<Resource<DetailMovieWithCast>>()
    val movieDetails: LiveData<Resource<DetailMovieWithCast>> = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = Resource.Loading()
            try {
                val result = movieUseCase.getDetailMovie(movieId)
                if (result is Resource.Success) {
                    result.data?.let {
                        _movieDetails.value = Resource.Success(it)
                    } ?: run {
                        _movieDetails.value = Resource.Error(DATA_IS_NULL)
                    }
                } else {
                    _movieDetails.value = Resource.Error(result.message ?: UNKNOWN_ERROR)
                }
            } catch (e: Exception) {
                _movieDetails.value = Resource.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }
}