package com.example.ninoaktivities.data.repository

import com.example.ninoaktivities.data.PlaceType
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

class PlaceRepository {
    private val _placesFlow = MutableStateFlow<List<Place>>(LocalPlacesDataProvider.places)
    val placesFlow = _placesFlow.asStateFlow()

    fun toggleFavorite(place: Place) {
        _placesFlow.value = _placesFlow.value.map { place ->
            if (place == place) {
                when (place) {
                    is Cafe -> place.copy(isFavorite = !place.isFavorite)
                    is Park -> place.copy(isFavorite = !place.isFavorite)
                    is Mall -> place.copy(isFavorite = !place.isFavorite)
                    is KidFriendly -> place.copy(isFavorite = !place.isFavorite)
                }
            } else { place }
        }
    }

    fun whichType(place: Place): PlaceType {
        return when (place) {
            is Cafe -> PlaceType.CAFE
            is Park -> PlaceType.PARK
            is Mall -> PlaceType.MALL
            is KidFriendly -> PlaceType.KID_FRIENDLY
        }
    }

    fun toMapOnType(): Map<PlaceType, List<Place>> {
        return _placesFlow.value.groupBy { whichType(it)}
    }
}