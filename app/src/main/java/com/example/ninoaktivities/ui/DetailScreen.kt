package com.example.ninoaktivities.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.ninoaktivities.R
import com.example.ninoaktivities.data.datasourse.LocalPlacesDataProvider
import com.example.ninoaktivities.data.model.Cafe
import com.example.ninoaktivities.data.model.KidFriendly
import com.example.ninoaktivities.data.model.Mall
import com.example.ninoaktivities.data.model.Park
import com.example.ninoaktivities.data.model.Place

@Composable
fun DetailScreen(
    place: Place?,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {

    BackHandler {
        onBackPressed()
    }
    Column(modifier = modifier) {
        if(place != null) {
            if (!isFullScreen) {
                DetailScreenTop(
                    title = stringResource(place.nameRes),
                    onBackPressed = onBackPressed
                )
            }
            LazyRow {
                items(place.photos) { photo ->
                    Image(
                        painter = painterResource(photo),
                        modifier = Modifier.height(250.dp).padding(end = 4.dp),
                        contentDescription = null,
                        contentScale = ContentScale.Inside
                    )
                }
            }
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TextInformation("Адресс:", place.address, Modifier.padding(8.dp))
                DetailPlaceTypeContent(place, Modifier.padding(8.dp))
                TextInformation(
                    "Описание:",
                    stringResource(place.description),
                    Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun DetailPlaceTypeContent(
    place: Place,
    modifier: Modifier = Modifier
) {
    when (place) {
        is Cafe ->
            Column {
                TextInformation("Кухня:", place.cuisine, modifier)
                TextInformation("Средний чек:", place.averageCheck, modifier)
            }

        is KidFriendly -> TextInformation("Минимальный возраст:", place.minAge.toString() + " " + if(place.minAge < 5 && place.minAge > 0) "года" else "лет", modifier)
        is Mall -> TextInformation("Время работы:", place.workingHours, modifier)
        is Park -> Column {
            TextInformation("Площадь:", place.area.toString() + " km^2", modifier)
            TextInformation("Район:", stringResource(place.district), modifier)
        }
    }
}

@Composable
fun TextInformation(title: String, text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun DetailScreenTop(
    title: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer), verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier.padding(14.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.displayLarge,
                fontSize = 26.sp
            )
        }
    }
}

@Preview(name = "DetailScreen")
@Composable
fun DetailScreenPreview() {
    AppTheme {
        Surface {
            DetailScreen(
                place = LocalPlacesDataProvider.places.get(0),
                onBackPressed = {}
            )
        }
    }
}

