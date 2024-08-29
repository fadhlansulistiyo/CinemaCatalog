package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.tvdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.TvUseCase
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_NULL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTvViewModel @Inject constructor(
    private val tvUseCase: TvUseCase
) : ViewModel() {

    private val _tvDetails = MutableLiveData<Resource<DetailTvWithCast>>()
    val tvDetails: LiveData<Resource<DetailTvWithCast>> = _tvDetails

    fun fetchTvDetails(tvId: Int) {
        viewModelScope.launch {
            _tvDetails.value = Resource.Loading()
            try {
                val result = tvUseCase.getDetailTv(tvId)
                if (result is Resource.Success) {
                    result.data?.let {
                        _tvDetails.value = Resource.Success(it)
                    } ?: run {
                        _tvDetails.value = Resource.Error(DATA_IS_NULL)
                    }
                } else {
                    _tvDetails.value = Resource.Error(result.message ?: UNKNOWN_ERROR)
                }
            } catch (e: Exception) {
                _tvDetails.value = Resource.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }
}