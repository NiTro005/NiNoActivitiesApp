package com.example.ninoaktivities.data.repository

import com.example.ninoaktivities.data.PlaceType
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.data.whichType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlaceRepository {
    private val _placesFlow = MutableStateFlow<List<Place>>(LocalPlacesDataProvider.places)
    val placesFlow = _placesFlow.asStateFlow()

    fun toggleFavorite(place: Place) {
        _placesFlow.value = _placesFlow.value.map { cur_place ->
            if (cur_place.id == place.id) {
                when (cur_place) {
                    is Cafe -> cur_place.copy(isFavorite = !cur_place.isFavorite)
                    is Park -> cur_place.copy(isFavorite = !cur_place.isFavorite)
                    is Mall -> cur_place.copy(isFavorite = !cur_place.isFavorite)
                    is KidFriendly -> cur_place.copy(isFavorite = !cur_place.isFavorite)
                }
            } else { cur_place }
        }
    }

    fun toMapOnType(): Map<PlaceType, List<Place>> {
        return _placesFlow.value.groupBy { it.whichType()}
    }

    fun allPlaces(): List<Place> {
        return _placesFlow.value
    }

    fun favoritePlaces() : List<Place> {
        return _placesFlow.value.filter { it.isFavorite }
    }
}