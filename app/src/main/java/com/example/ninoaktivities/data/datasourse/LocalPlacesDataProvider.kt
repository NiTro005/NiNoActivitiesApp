package com.example.ninoaktivities.data.datasourse

import com.example.ninoaktivities.R
import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place

object LocalPlacesDataProvider {
    val places = listOf(
        // КАФЕ
        Cafe(
            id = 1,
            nameRes = R.string.cafe_biblioteka,
            description = R.string.cafe_biblioteka_desc,
            address = "ул. Большая Покровская, 15",
            rating = 4.7f,
            photos = listOf(R.drawable.biblioteca_1, R.drawable.biblioteca_2, R.drawable.biblioteca_3, R.drawable.biblioteca_4),
            cuisine = "Европейская",
            averageCheck = "1200-2000 ₽",
            hasWifi = true
        ),
        Cafe(
            id = 2,
            nameRes = R.string.cafe_lepi_testo,
            description = R.string.cafe_lepi_testo_desc,
            address = "ул. Минина, 12",
            rating = 4.5f,
            photos = listOf(R.drawable.lepi_testo_1, R.drawable.lepi_testo_2, R.drawable.lepi_testo_3, R.drawable.lepi_testo_4),
            cuisine = "Итальянская",
            averageCheck = "800-1500 ₽",
            hasWifi = true
        ),
        Cafe(
            id = 3,
            nameRes = R.string.cafe_karma,
            description = R.string.cafe_karma_desc,
            address = "ул. Большая Покровская, 47А",
            rating = 4.8f,
            photos = listOf(R.drawable.carma_1, R.drawable.carma_2, R.drawable.carma_3, R.drawable.carma_4),
            cuisine = "Азиатская",
            averageCheck = "1000-1800 ₽",
            hasWifi = true
        ),

        // ПАРКИ
        Park(
            id = 101,
            nameRes = R.string.park_800_letiya,
            description = R.string.park_800_letiya_desc,
            address = "наб. Федоровского",
            rating = 4.6f,
            photos = listOf(R.drawable.park_800_1, R.drawable.park_800_2),
            area = 58.0f,
            district = R.string.sovetsky_district
        ),
        Park(
            id = 102,
            nameRes = R.string.park_shveitsariya,
            description = R.string.park_shveitsariya_desc,
            address = "парк Швейцария",
            rating = 4.8f,
            photos = listOf(R.drawable.shveytsaria_1, R.drawable.shveytsaria_2, R.drawable.shveytsaria_3),
            area = 380.0f,
            district = R.string.prioksky_district
        ),
        Park(
            id = 103,
            nameRes = R.string.park_shchelkovsky,
            description = R.string.park_shchelkovsky_desc,
            address = "Щелковский хутор, 1",
            rating = 4.7f,
            photos = listOf(R.drawable.shelkovskiy_1, R.drawable.shelkovskiy_2, R.drawable.shelkovskiy_3),
            area = 45.0f,
            district = R.string.nizhny_novgorod_district
        ),

        // ТОРГОВЫЕ ЦЕНТРЫ
        Mall(
            id = 201,
            nameRes = R.string.mall_nebo,
            description = R.string.mall_nebo_desc,
            address = "ул. Большая Покровская, 82",
            rating = 4.5f,
            photos = listOf(R.drawable.nebo_1, R.drawable.nebo_2, R.drawable.nebo_3),
            workingHours = "10:00-22:00"
        ),
        Mall(
            id = 202,
            nameRes = R.string.mall_fantastika,
            description = R.string.mall_fantastika_desc,
            address = "ул. Родионова, 187",
            rating = 4.6f,
            photos = listOf(R.drawable.fantastica_1, R.drawable.fantastica_2),
            workingHours = "10:00-22:00"
        ),
        Mall(
            id = 203,
            nameRes = R.string.mall_rio,
            description = R.string.mall_rio_desc,
            address = "Московское шоссе, 12",
            rating = 4.4f,
            photos = listOf(R.drawable.rio_1, R.drawable.rio_2, R.drawable.rio_3),
            workingHours = "10:00-22:00"
        ),

        // МЕСТА ДЛЯ ДЕТЕЙ
        KidFriendly(
            id = 301,
            nameRes = R.string.kids_ostrov_geroev,
            description = R.string.kids_ostrov_geroev_desc,
            address = "Сормовский парк",
            rating = 4.8f,
            photos = listOf(R.drawable.kids_ostrov_geroev_1, R.drawable.kids_ostrov_geroev_2, R.drawable.kids_ostrov_geroev_3),
            minAge = 3,
            isFree = false
        ),
        KidFriendly(
            id = 302,
            nameRes = R.string.kids_kosmopark,
            description = R.string.kids_kosmopark_desc,
            address = "ул. Родионова, 165",
            rating = 4.7f,
            photos = listOf(R.drawable.kids_kosmopark_1, R.drawable.kids_kosmopark_2, R.drawable.kids_kosmopark_3, R.drawable.kids_kosmopark_4),
            minAge = 4,
            isFree = false
        ),
        KidFriendly(
            id = 303,
            nameRes = R.string.kids_atlantida,
            description = R.string.kids_atlantida_desc,
            address = "ул. Октябрьской Революции, 31, корп. 3",
            rating = 4.6f,
            photos = listOf(R.drawable.atlantida_1, R.drawable.atlantida_2, R.drawable.atlantida_3),
            minAge = 2,
            isFree = false
        ),
        KidFriendly(
            id = 304,
            nameRes = R.string.zoo_limpopo,
            description = R.string.zoo_limpopo_desc,
            address = "ул. Ярошенко, 7Г, корп. 1",
            rating = 4.9f,
            photos = listOf(R.drawable.limpopo_1, R.drawable.limpopo_2, R.drawable.limpopo_3),
            minAge = 0,
            isFree = false
        ),
        KidFriendly(
            id = 305,
            nameRes = R.string.aquapark_okeanis,
            description = R.string.aquapark_okeanis_desc,
            address = "просп. Гагарина, 35, корп. 1",
            rating = 4.8f,
            photos = listOf(R.drawable.akvapark_okeanis_1, R.drawable.akvapark_okeanis_2, R.drawable.akvapark_okeanis_3, R.drawable.akvapark_okeanis_4),
            minAge = 3,
            isFree = false
        ),
        KidFriendly(
            id = 306,
            nameRes = R.string.planetarium,
            description = R.string.planetarium_desc,
            address = "ул. Революционная, 20",
            rating = 4.7f,
            photos = listOf(R.drawable.planetariy, R.drawable.planetariy_2, R.drawable.planetariy_3),
            minAge = 6,
            isFree = false
        )
    )
}