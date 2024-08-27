package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_EMPTY
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NETWORK_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NO_INTERNET_CONNECTION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

open class BaseRemoteDataSource {

    protected suspend fun <T> handleApiCall(apiCall: suspend () -> T): ApiResponseResult<T> {
        return try {
            val response = apiCall()
            ApiResponseResult.Success(response)
        } catch (e: Exception) {
            when (e) {
                is IOException -> ApiResponseResult.Error(NO_INTERNET_CONNECTION)
                is HttpException -> ApiResponseResult.Error("$NETWORK_ERROR: ${e.message}")
                else -> ApiResponseResult.Error(e.toString())
            }
        }
    }

    protected fun <T> flowApiCall(apiCall: suspend () -> T): Flow<ApiResponseResult<T>> {
        return flow {
            emit(handleApiCall(apiCall))
        }.flowOn(Dispatchers.IO)
    }

    class EmptyDataException : Exception(DATA_IS_EMPTY)
}
