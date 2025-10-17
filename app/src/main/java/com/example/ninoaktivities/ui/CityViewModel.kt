package com.example.ninoaktivities.ui

import androidx.lifecycle.ViewModel
import com.example.ninoaktivities.data.PlaceType
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.data.repository.PlaceRepository
import com.example.ninoaktivities.ui.utils.IconType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val data = PlaceRepository()

    private val placesByScreen: Map<IconType, List<Place>> = createMapPlacesScreen(
        data.toMapOnType(),
        data.shufflePlaces(),
        data.favoritePlaces()
    )


    private val _uiState = MutableStateFlow(CityUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                currentPlaces = placesByScreen[IconType.ALL] ?: emptyList(),
                currentPlace = placesByScreen[IconType.ALL]?.get(0) ?: LocalPlacesDataProvider.defaultPlace
            )
        }
    }

    private fun createMapPlacesScreen(
        placeMap: Map<PlaceType, List<Place>>,
        allShufflePlaces: List<Place>,
        favoritePlaces: List<Place>): Map<IconType, List<Place>> {

        val new_map = placeMap.mapKeys { (key, _) ->
            when(key) {
                PlaceType.CAFE -> IconType.CAFE
                PlaceType.PARK -> IconType.PARK
                PlaceType.MALL -> IconType.MALL
                PlaceType.KID_FRIENDLY -> IconType.KID_FRIENDLY
            }
        }.toMutableMap()

        new_map[IconType.ALL] = allShufflePlaces
        new_map[IconType.FAVORITE] = favoritePlaces
        return new_map.toMap()
    }


    fun clickOnPlace(place: Place) {
        _uiState.update {
            it.copy(
                currentPlace = place,
                isHome = false
            )
        }
    }

    fun clickOnIcon(icon: IconType) {
        _uiState.update {
            it.copy(
                currentIconType = icon,
                currentPlaces = placesByScreen[icon] ?: emptyList()
            )
        }
    }

    fun clickOnStar(place: Place) = data.toggleFavorite(place)

    fun resetHomeStates() {
        _uiState.update {
            it.copy(
                currentPlace = placesByScreen[_uiState.value.currentIconType]?.get(0) ?: data.placesFlow.value.get(0),
                isHome = true
            )
        }
    }
}