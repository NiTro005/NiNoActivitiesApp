package com.example.ninoaktivities.ui

import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.ui.utils.IconType

data class CityUiState (
    val currentIconType: IconType = IconType.ALL,
    val currentPlaces: List<Place> = emptyList(),
    val currentPlace: Place = LocalPlacesDataProvider.defaultPlace,

    val isHome: Boolean = true
)
