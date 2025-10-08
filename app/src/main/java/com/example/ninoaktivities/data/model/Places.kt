package com.example.ninoaktivities.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Place {
    abstract val id: Int
    @get:StringRes abstract val nameRes: Int
    @get:StringRes abstract val description: Int
    abstract val address: String
    abstract val rating: Float
    abstract val photos: List<Int>
}

data class Cafe(
    override val id: Int,
    @get:StringRes override val nameRes: Int,
    @get:StringRes override val description: Int,
    override val address: String,
    override val rating: Float,
    override val photos: List<Int>,
    val cuisine: String,
    val averageCheck: String,
    val hasWifi: Boolean
) : Place()

data class Park(
    override val id: Int,
    @get:StringRes override val nameRes: Int,
    @get:StringRes override val description: Int,
    override val address: String,
    override val rating: Float,
    override val photos: List<Int>,
    val area: Float,
    @StringRes val district: Int
) : Place()

data class Mall(
    override val id: Int,
    @get:StringRes override val nameRes: Int,
    @get:StringRes override val description: Int,
    override val address: String,
    override val rating: Float,
    override val photos: List<Int>,
    val workingHours: String,
) : Place()

data class KidFriendly(
    override val id: Int,
    @get:StringRes override val nameRes: Int,
    @get:StringRes override val description: Int,
    override val address: String,
    override val rating: Float,
    override val photos: List<Int>,
    val minAge: Int,
    val isFree: Boolean
) : Place()