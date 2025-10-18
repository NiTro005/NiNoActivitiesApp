package com.example.ninoaktivities.ui

import androidx.annotation.GuardedBy
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.ui.utils.IconType

data class CityUiState (
    val placesByScreen: Map<IconType, List<Place>> = emptyMap(),
    val currentIconType: IconType = IconType.ALL,
    val currentPlace: Place = LocalPlacesDataProvider.defaultPlace,

    val isHome: Boolean = true
) {
    val currentPlaces: List<Place> by lazy { placesByScreen[currentIconType] ?: emptyList() }
}
