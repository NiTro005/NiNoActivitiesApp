package com.example.ninoaktivities.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CityApp(
    modifier: Modifier = Modifier
) {
    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value
    HomeScreen(
        uiState = cityUiState,
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