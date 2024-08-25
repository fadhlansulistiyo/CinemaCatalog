package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {

    override fun getNowPlaying(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlaying()
    }

    override suspend fun getDetailMovie(movieId: Int): Resource<DetailMovieWithCast> {
        return movieRepository.getDetailMovie(movieId)
    }
}