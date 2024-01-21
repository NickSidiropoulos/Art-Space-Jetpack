package com.nicksidiropoulos.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicksidiropoulos.artspace.ui.theme.ArtSpaceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceWall()
            }
        }
    }
}

@Composable
fun ArtSpaceWall(modifier: Modifier = Modifier) {

    var artWorkPosition by remember { mutableIntStateOf(1) }
    var artwork = getArtWork(artWorkPosition)

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Artwork(art = artwork.first)
        Description(
            painting = stringResource(id = artwork.second),
            artistAndYear = stringResource(id = artwork.third)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { artWorkPosition = changeArtWork(artWorkPosition, "prev") }) {
                Text(text = stringResource(R.string.previous))
            }
            Button(onClick = { artWorkPosition = changeArtWork(artWorkPosition, "next") }) {
                Text(text = stringResource(R.string.next))
            }
        }
    }

}

fun changeArtWork(artworkposition: Int, direction: String): Int {
    when (direction) {
        "next" -> {
            return if (artworkposition == 4) {
                1
            } else {
                artworkposition + 1
            }
        }

        else -> {
            return if (artworkposition == 1) {
                4
            } else {
                artworkposition - 1
            }
        }
    }
}

fun getArtWork(artWorkPosition: Int): Triple<Int, Int, Int> {
    return when (artWorkPosition) {
        1 -> Triple(R.drawable.one, R.string.painting_one, R.string.artistAndYear_one)
        2 -> Triple(R.drawable.two, R.string.painting_two, R.string.artistAndYear_two)
        3 -> Triple(R.drawable.three, R.string.painting_three, R.string.artistAndYear_three)
        else -> Triple(R.drawable.four, R.string.painting_four, R.string.artistAndYear_four)
    }
}


@Composable
fun Artwork(art: Int, modifier: Modifier = Modifier) {
    Surface(
        Modifier.border(2.dp, Color.Black)
    ) {
        Image(
            painter = painterResource(id = art),
            contentDescription = null,
            Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)
        )
    }
}


@Composable
fun Description(painting: String, artistAndYear: String, modifier: Modifier = Modifier) {
    Column(
        Modifier.padding(20.dp)
    ) {
        Text(text = painting, fontSize = 30.sp)
        Text(text = artistAndYear, fontSize = 20.sp)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpaceWallPreview() {
    ArtSpaceTheme {
        ArtSpaceWall()
    }
}