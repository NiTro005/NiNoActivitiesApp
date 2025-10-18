package com.example.ninoaktivities.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.ninoaktivities.R
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.ui.utils.IconType
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(
    uiState: CityUiState,
    onTabPressed: (IconType) -> Unit,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val navItemList = listOf<NavItem>(
        NavItem(type = IconType.ALL, icon = Icons.Outlined.Home, text = stringResource(R.string.home)),
        NavItem(type = IconType.CAFE, icon = Icons.Outlined.Coffee, text = stringResource(R.string.cafe)),
        NavItem(type = IconType.PARK, icon = Icons.Outlined.Nature, text = stringResource(R.string.park)),
        NavItem(type = IconType.MALL, icon = Icons.Outlined.LocalMall, text = stringResource(R.string.mall)),
        NavItem(type = IconType.KID_FRIENDLY, icon = Icons.Outlined.ChildFriendly, text = stringResource(R.string.kid_friendly)),
        NavItem(type = IconType.FAVORITE, icon = Icons.Outlined.Star, text = stringResource(R.string.favorite))
    )
    if (uiState.isHome) {
        CityAppContent(
            uiState = uiState,
            navItemList = navItemList,
            onTabPressed = onTabPressed,
            onCardPressed = onCardPressed,
            onStarPressed = onStarPressed,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        DetailScreen()
    }
}

@Composable
fun CityAppContent(
    uiState: CityUiState,
    navItemList: List<NavItem>,
    onTabPressed: (IconType) -> Unit,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OnlyListContent(
            uiState = uiState,
            onCardPressed = onCardPressed,
            onStarPressed = onStarPressed,
            modifier = Modifier.padding(horizontal = 16.dp).weight(1f)
        )
        CityBottomBar(
            currentItemType = uiState.currentIconType,
            navItemList = navItemList,
            onTabPressed = onTabPressed,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CityBottomBar(
    currentItemType: IconType,
    navItemList: List<NavItem>,
    onTabPressed: (IconType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navItemList) {
            NavigationBarItem(
                selected = currentItemType == navItem.type,
                onClick = { onTabPressed(navItem.type) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

data class NavItem (
    val type: IconType,
    val icon: ImageVector,
    val text: String
)
