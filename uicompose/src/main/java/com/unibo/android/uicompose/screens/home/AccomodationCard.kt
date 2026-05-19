package com.unibo.android.uicompose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.unibo.android.domain.models.AccommodationType
import com.unibo.android.uicompose.R
import com.unibo.android.uicompose.extensions.toResId

@Composable
fun AccomodationCard(
    accomodationType: AccommodationType,
    onItemClick: (AccommodationType) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .clickable { onItemClick(accomodationType) }
    ) {
        Column {
            Row {
                // --- AGGIUNTA IMMAGINE ---
                AsyncImage(
                    model = accomodationType.pictureUrl,
                    contentDescription = null, // Immagine descrittiva, il testo accanto spiega già tutto
                    modifier = Modifier
                        .size(100.dp) // Dimensione quadrata per l'anteprima
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop, // Fondamentale per non deformare la foto
                    placeholder = painterResource(R.drawable.ic_home), // Icona mentre carica
                    // error = painterResource(R.drawable.ic_broken_image) // Icona se l'URL è sbagliato
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimensionResource(R.dimen.spacing_small))
                ) {
                    val type = stringResource(accomodationType.toResId())

                    Text(
                        type,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    )
                    Text(
                        accomodationType.name,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = Color.Blue)
                ) {
                    Text(
                        accomodationType.score.toString(),
                        modifier = Modifier.padding(8.dp),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
            Text(
                accomodationType.description,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
            )
        }
    }
}

@Preview
@Composable
fun AccomodationCardPreview() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        AccomodationCard(
            accomodationType = AccommodationType.Hotel(
                hotelName = "Nome Hotel Nuovo",
                hotelDescription = "Questa è la descrizione dell'hotel",
                hotelPictureUrl = "",
                hotelScore = 7.3,
            ),
            onItemClick = {}
        )
    }
}