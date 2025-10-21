package com.example.ninoaktivities.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChildFriendly
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.Nature
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.ninoaktivities.R
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.ui.utils.CityContentType
import com.example.ninoaktivities.ui.utils.CityNavigationType
import com.example.ninoaktivities.ui.utils.IconType

@Composable
fun HomeScreen(
    uiState: CityUiState,
    navigationType: CityNavigationType,
    contentType: CityContentType,
    onTabPressed: (IconType) -> Unit,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    onBackPressed: () -> Unit,
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
    if(navigationType == CityNavigationType.DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(modifier = Modifier.width(240.dp)) {
                    CityNavigationDrawer(
                        currentItemType = uiState.currentIconType,
                        onTabPressed = onTabPressed,
                        navItemList = navItemList,
                        modifier = Modifier.fillMaxHeight().wrapContentWidth().padding(12.dp)
                    )
                }
            }
        ) {
            CityAppContent(
                uiState = uiState,
                navigationType = navigationType,
                contentType = contentType,
                navItemList = navItemList,
                onTabPressed = onTabPressed,
                onCardPressed = onCardPressed,
                onStarPressed = onStarPressed,
                modifier = Modifier.fillMaxSize()
            )
        }
    } else {
        if (uiState.isHome) {
            CityAppContent(
                uiState = uiState,
                navigationType = navigationType,
                contentType = contentType,
                navItemList = navItemList,
                onTabPressed = onTabPressed,
                onCardPressed = onCardPressed,
                onStarPressed = onStarPressed,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            DetailScreen(
                uiState.currentPlace,
                onBackPressed = onBackPressed,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun CityAppContent(
    uiState: CityUiState,
    navigationType: CityNavigationType,
    contentType: CityContentType,
    navItemList: List<NavItem>,
    onTabPressed: (IconType) -> Unit,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        AnimatedVisibility(visible = navigationType == CityNavigationType.RAIL) {
            CityNavigationRail(
                currentItemType = uiState.currentIconType,
                navItemList = navItemList,
                onTabPressed = onTabPressed,
                modifier = Modifier.fillMaxHeight()
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            if (contentType == CityContentType.LIST) {
                OnlyListContent(
                    uiState = uiState,
                    onCardPressed = onCardPressed,
                    onStarPressed = onStarPressed,
                    modifier = Modifier.padding(horizontal = 16.dp).weight(1f)
                )
            } else {
                TODO()
            }
            AnimatedVisibility(visible = navigationType == CityNavigationType.BOTTOM) {
                CityBottomBar(
                    currentItemType = uiState.currentIconType,
                    navItemList = navItemList,
                    onTabPressed = onTabPressed,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
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

@Composable
fun CityNavigationRail(
    currentItemType: IconType,
    navItemList: List<NavItem>,
    onTabPressed: (IconType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (item in navItemList) {
            NavigationRailItem(
                selected = currentItemType == item.type,
                onClick = { onTabPressed(item.type) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.text
                    )
                }
            )
        }

    }
}


@Composable
fun CityNavigationDrawer(
    currentItemType: IconType,
    navItemList: List<NavItem>,
    onTabPressed: (IconType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CityItemsHeader(
            modifier = Modifier.height(60.dp).fillMaxWidth(),
            scale = 0.8f
        )
        for (item in navItemList) {
            NavigationDrawerItem(
                selected = currentItemType == item.type,
                label = {
                    Text(
                        text = item.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.text
                    )
                },
                onClick = { onTabPressed(item.type) },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                )
            )
        }
    }
}

data class NavItem (
    val type: IconType,
    val icon: ImageVector,
    val text: String
)

@Preview
@Composable
fun NavDrawerPreview() {
    AppTheme {
        Surface {
            CityNavigationDrawer(
                currentItemType = IconType.ALL,
                navItemList = listOf<NavItem>(
                    NavItem(
                        type = IconType.ALL,
                        icon = Icons.Outlined.Home,
                        text = stringResource(R.string.home)
                    ),
                    NavItem(
                        type = IconType.CAFE,
                        icon = Icons.Outlined.Coffee,
                        text = stringResource(R.string.cafe)
                    ),
                    NavItem(
                        type = IconType.PARK,
                        icon = Icons.Outlined.Nature,
                        text = stringResource(R.string.park)
                    ),
                    NavItem(
                        type = IconType.MALL,
                        icon = Icons.Outlined.LocalMall,
                        text = stringResource(R.string.mall)
                    ),
                    NavItem(
                        type = IconType.KID_FRIENDLY,
                        icon = Icons.Outlined.ChildFriendly,
                        text = stringResource(R.string.kid_friendly)
                    ),
                    NavItem(
                        type = IconType.FAVORITE,
                        icon = Icons.Outlined.Star,
                        text = stringResource(R.string.favorite)
                    )
                ),
                onTabPressed = {},
                modifier = Modifier.fillMaxHeight().wrapContentWidth().padding(12.dp)
            )
        }
    }
}
