package com.example.ninoaktivities.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.computeHorizontalBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.ninoaktivities.R
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place

@Composable
fun OnlyListContent(
    uiState: CityUiState,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = uiState.currentPlaces
    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            CityItemsHeader(
                modifier = Modifier.height(60.dp).fillMaxWidth()
            )
        }
        if(places.isNotEmpty()) {
            itemsIndexed(places, key = { index, place -> place.id }) { index, place ->
                val orientation = index % 2 == 0
                PlaceCard(
                    place = place,
                    selected = false,
                    orientation = orientation,
                    onCardPressed = { onCardPressed(place) },
                    onStarPressed = { onStarPressed(place) },
                    isFavorite = place.isFavorite,
                    modifier = Modifier.height(130.dp).animateItem()
                )
            }
        } else {
            item {
                EmptyListMessage(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ListAndDetailContent(
    uiState: CityUiState,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = uiState.currentPlaces
    val context = LocalContext.current
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            contentPadding = WindowInsets.statusBars.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (places.isNotEmpty()) {
                itemsIndexed(places, key = { index, place -> place.id }) { index, place ->
                    val orientation = index % 2 == 0
                    PlaceCard(
                        place = place,
                        selected = uiState.currentPlace == place,
                        orientation = orientation,
                        onCardPressed = { onCardPressed(place) },
                        onStarPressed = { onStarPressed(place) },
                        isFavorite = place.isFavorite,
                        modifier = Modifier.height(130.dp).animateItem()
                    )
                }
            } else {
                item {
                    EmptyListMessage(modifier = Modifier.fillMaxSize())
                }
            }
        }
        DetailScreen(
            place = uiState.currentPlace,
            onBackPressed = { (context  as? Activity)?.finish()},
            modifier = Modifier.weight(1f),
            isFullScreen = true
        )
    }
}

@Composable
fun EmptyListMessage(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = "Здесь пока пусто",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun CityItemsHeader(modifier: Modifier = Modifier, scale: Float = 1f) {
    Row(
        modifier = modifier.graphicsLayer(
            scaleX = scale,
            scaleY = scale
        ),
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "NiNo",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "activities",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(end = 8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Image(
            painter = painterResource(R.drawable.park_rides),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PlaceCard(
    place: Place,
    selected: Boolean,
    orientation: Boolean,
    onCardPressed: () -> Unit,
    onStarPressed: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (!selected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = onCardPressed
    ) {
        Box {
            when(place) {
                is Cafe -> PlaceContentOnCard(
                    place = place,
                    orientation = orientation,
                    service = Icons.Filled.Wifi
                )
                is KidFriendly -> PlaceContentOnCard(
                    place = place,
                    orientation = orientation,
                    service = Icons.Default.AttachMoney
                )
                is Mall -> PlaceContentOnCard(
                    place = place,
                    orientation = orientation
                )
                is Park -> PlaceContentOnCard(
                    place = place,
                    orientation = orientation
                )
            }
            IconButton(
                onClick = onStarPressed,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite ) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = if (isFavorite) Color.Red else LocalContentColor.current
                )
            }
        }
    }
}

@Composable
fun PlaceContentOnCard(
    place: Place,
    service: ImageVector? = null,
    orientation: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        if (orientation) {
            DrawImage(place, Modifier.weight(1f), serviсe = service)
            PlaceText(
                title = stringResource(place.nameRes),
                address = place.address,
                modifier = Modifier
                .weight(1f)
                .padding(8.dp))
        } else {
            PlaceText(
                title = stringResource(place.nameRes),
                address = place.address,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp))
            DrawImage(place, Modifier.weight(1f), serviсe = service)
        }
    }
}

@Composable
fun PlaceText(
    title: String,
    address: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(top = 8.dp)
        )
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Row {
                Text(
                    text = address,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
fun DrawImage(
    place: Place,
    modifier: Modifier = Modifier,
    serviсe: ImageVector? = null
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val textColor = MaterialTheme.colorScheme.onSurface
    Box(modifier = modifier) {
        Image(
            painter = painterResource(place.photos.first()),
            contentDescription = stringResource(place.nameRes),
            contentScale = ContentScale.Crop
        )
        if(serviсe != null) {
            Icon(
                imageVector = serviсe,
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomStart)
                    .padding(4.dp)
                    .background(
                        color = backgroundColor.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(30.dp),
                tint = textColor
            )
        }
        Text(
            text = place.rating.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(4.dp)
                .background(
                    color = backgroundColor.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(4.dp)
                ),
            color = textColor
        )
    }
}

@Preview(name = "Place card")
@Composable
fun PlaceCardPreview() {
    AppTheme {
        PlaceCard(
            place = LocalPlacesDataProvider.places.get(0),
            selected = false,
            onCardPressed = { },
            onStarPressed =  { },
            orientation = true,
            isFavorite = true,
            modifier = Modifier.height(120.dp)
        )
    }
}

@Preview(name = "Place card reversed")
@Composable
fun PlaceCardReversedPreview() {
    AppTheme(darkTheme = true) {
        PlaceCard(
            place = LocalPlacesDataProvider.places.get(10),
            selected = false,
            onCardPressed = { },
            onStarPressed =  { },
            orientation = false,
            isFavorite = false,
            modifier = Modifier.height(120.dp)
        )
    }
}

