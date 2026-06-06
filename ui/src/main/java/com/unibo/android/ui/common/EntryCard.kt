package com.unibo.android.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.unibo.android.ui.theme.Background

@Composable
fun EntryCard(
    title: String,
    onClick: () -> Unit,
    isPassword: Boolean? = null,
    iconUrl: String? = null
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!iconUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = iconUrl,
                    imageLoader = imageLoader,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp).padding(2.dp),
                    onSuccess = {
                        println("SUCCESS")
                    },
                    onError = {
                        println("ERROR")
                    }
                )
            }

            Text(
                text = title,
                modifier = Modifier.padding(12.dp)
            )

            isPassword?.let {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (it) Color.Red else Color.Green,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}