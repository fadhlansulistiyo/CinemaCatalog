package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.tvdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.TvUseCase
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.WatchlistTvUseCase
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_NULL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTvViewModel @Inject constructor(
    private val tvUseCase: TvUseCase,
    private val watchlistTvUSeCase: WatchlistTvUseCase
) : ViewModel() {

    private val _detailTv = MutableLiveData<Resource<DetailTvWithCast>>()
    val detailTv: LiveData<Resource<DetailTvWithCast>> = _detailTv

    private val _isWatchlist = MutableLiveData<Boolean>()
    val isWatchlist: LiveData<Boolean> get() = _isWatchlist

    fun fetchTvDetails(tvId: Int) {
        viewModelScope.launch {
            _detailTv.value = Resource.Loading()
            try {
                val result = tvUseCase.getDetailTv(tvId)
                if (result is Resource.Success) {
                    result.data?.let {
                        _detailTv.value = Resource.Success(it)
                        checkIfWatchlist(it.detail.name)
                    } ?: run {
                        _detailTv.value = Resource.Error(DATA_IS_NULL)
                    }
                } else {
                    _detailTv.value = Resource.Error(result.message ?: UNKNOWN_ERROR)
                }
            } catch (e: Exception) {
                _detailTv.value = Resource.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }

    fun toggleWatchlistTv(watchlistTv: WatchlistTv) {
        viewModelScope.launch {
            val watchlist = watchlistTvUSeCase.getWatchlistByTitle(watchlistTv.name)
            if (watchlist == null) {
                watchlistTvUSeCase.addWatchlist(watchlistTv)
                _isWatchlist.postValue(true)
            } else {
                watchlistTvUSeCase.removeWatchlist(watchlistTv)
                _isWatchlist.postValue(false)
            }
        }
    }

    private fun checkIfWatchlist(title: String) {
        viewModelScope.launch {
            val watchlist = watchlistTvUSeCase.getWatchlistByTitle(title)
            _isWatchlist.postValue(watchlist != null)
        }
    }
}