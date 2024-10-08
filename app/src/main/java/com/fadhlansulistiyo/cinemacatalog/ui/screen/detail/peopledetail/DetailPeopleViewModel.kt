package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.peopledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailPeopleWithCredits
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.PeopleUseCase
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_NULL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPeopleViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase
) : ViewModel() {

    private val _detailPeople = MutableLiveData<Resource<DetailPeopleWithCredits>>()
    val detailPeople: LiveData<Resource<DetailPeopleWithCredits>> get() = _detailPeople

    fun fetchDetailPeople(peopleId: Int) {
        viewModelScope.launch {
            _detailPeople.value = Resource.Loading()
            try {
                val result = peopleUseCase.getDetailPeople(peopleId)
                if (result is Resource.Success) {
                    result.data?.let { detailPeople ->
                        _detailPeople.value = Resource.Success(detailPeople)
                    } ?: run {
                        _detailPeople.value = Resource.Error(DATA_IS_NULL)
                    }
                } else {
                    _detailPeople.value = Resource.Error(result.message ?: UNKNOWN_ERROR)
                }
            } catch (e: Exception) {
                _detailPeople.value = Resource.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }
}