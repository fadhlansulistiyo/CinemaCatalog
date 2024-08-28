package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People

@Composable
fun TrendingPeopleList(
    modifier: Modifier = Modifier,
    trendingPeopleState: Resource<List<People>>,
    navigateToDetails: (Int) -> Unit,
) {
    when (trendingPeopleState) {
        is Resource.Loading -> {
            LoadingItem()
        }

        is Resource.Success -> {
            val trendingPeople = trendingPeopleState.data

            if (!trendingPeople.isNullOrEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = modifier
                ) {
                    items(trendingPeople) { people ->
                        TrendingPeopleItem(
                            people = people,
                            onClick = { navigateToDetails(people.id) }
                        )
                    }
                }
            } else {
                Text(
                    text = "No trending people available",
                )
            }
        }

        is Resource.Error -> {
            trendingPeopleState.message?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
