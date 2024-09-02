package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.GenresDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.ProductionCompaniesDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Genres
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.ProductionCompanies
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NA

object BaseMapper {

    fun mapGenresResponseToDomain(input: GenresDTO?): Genres {
        return Genres(
            id = input?.id ?: 0,
            name = input?.name ?: NA
        )
    }

    fun mapProductionCompaniesResponseToDomain(input: ProductionCompaniesDTO?): ProductionCompanies {
        return ProductionCompanies(
            id = input?.id ?: 0,
            name = input?.name ?: NA
        )
    }
}