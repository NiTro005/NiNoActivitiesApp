package com.example.ninoaktivities.data

import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place

fun Place.whichType(): PlaceType {
    return when (this) {
        is Cafe -> PlaceType.CAFE
        is Park -> PlaceType.PARK
        is Mall -> PlaceType.MALL
        is KidFriendly -> PlaceType.KID_FRIENDLY
    }
}