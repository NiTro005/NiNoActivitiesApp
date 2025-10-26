package com.example.ninoaktivities.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.WindowRecomposerPolicy
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ninoaktivities.ui.utils.CityContentType
import com.example.ninoaktivities.ui.utils.CityNavigationType

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass
) {
    val contentType: CityContentType
    val navigationType: CityNavigationType
    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value

    when(windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = CityContentType.LIST
            navigationType = CityNavigationType.BOTTOM
        }
        WindowWidthSizeClass.Medium -> {
            contentType = CityContentType.LIST
            navigationType = CityNavigationType.RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = CityContentType.LIST_AND_DETAIL
            navigationType = CityNavigationType.DRAWER
        }
        else -> {
            contentType = CityContentType.LIST
            navigationType = CityNavigationType.BOTTOM
        }
    }

    HomeScreen(
        uiState = cityUiState,
        navigationType = navigationType,
        contentType = contentType,
        onTabPressed = { icon ->
            viewModel.clickOnIcon(icon)
            viewModel.resetHomeStates()
        },
        onCardPressed = { place ->
            viewModel.clickOnPlace(place)
        },
        onStarPressed = { place ->
            viewModel.clickOnStar(place)
        },
        onBackPressed = {
            viewModel.resetHomeStates()
        }
    )
}