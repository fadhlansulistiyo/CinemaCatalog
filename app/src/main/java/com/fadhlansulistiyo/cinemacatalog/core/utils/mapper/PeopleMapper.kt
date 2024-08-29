package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.PeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People

fun PeopleDTO.toDomainModel(): People {
    return People(
        id = this.id,
        name = this.name.orEmpty(),
        profilePath = this.profilePath ?: ""
    )
}