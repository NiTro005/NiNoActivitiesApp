package com.example.ninoaktivities.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place
import com.example.ninoaktivities.ui.utils.IconType
import java.nio.file.WatchEvent

@Composable
fun OnlyListContent(
    uiState: CityUiState,
    onCardPressed: (Place) -> Unit,
    onStarPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    var orientation by rememberSaveable { mutableStateOf(true) }
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
            containerColor = if (selected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.secondaryContainer
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
                .padding(16.dp))
        } else {
            PlaceText(
                title = stringResource(place.nameRes),
                address = place.address,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp))
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
    Box(modifier = modifier) {
        Image(
            painter = painterResource(place.photos.get(0)),
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
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
        Text(
            text = place.rating.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(4.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(4.dp)
                ),
            color = MaterialTheme.colorScheme.inverseOnSurface
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
    AppTheme {
        PlaceCard(
            place = LocalPlacesDataProvider.places.get(10),
            selected = true,
            onCardPressed = { },
            onStarPressed =  { },
            orientation = false,
            isFavorite = false,
            modifier = Modifier.height(120.dp)
        )
    }
}
