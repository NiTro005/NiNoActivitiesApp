package com.example.ninoaktivities.ui

import com.example.ninoaktivities.data.PlaceType
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.ui.utils.IconType

data class CityUiState (
    val currentIconType: IconType = IconType.ALL,
    val currentPlace: Place = LocalPlacesDataProvider.places[0],

    val isHome: Boolean = true
)
