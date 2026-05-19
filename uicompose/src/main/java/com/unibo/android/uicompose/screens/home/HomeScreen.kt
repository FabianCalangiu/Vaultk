package com.unibo.android.uicompose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.unibo.android.domain.models.AccommodationType
import com.unibo.android.uicompose.R
import com.unibo.android.uicompose.common.Loader
import kotlin.text.get

@Composable
fun HomeScreen(
    viewModel: HomepageViewModel,
    showDetails: (AccommodationType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val showLoader = viewModel.showLoader.collectAsStateWithLifecycle()
    val accomodationTypeList = viewModel.accommodationTypeList.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        if (showLoader.value) Loader()
        else if (accomodationTypeList.value.isEmpty()) {
            Text(
                text = stringResource(R.string.home_empty_list),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                HeaderSection()

                LazyColumn(
                    contentPadding = PaddingValues(all = 16.dp), // Padding interno per staccare dai bordi
                    verticalArrangement = Arrangement.spacedBy(12.dp) // Spazio tra le card
                ) {

                    items(accomodationTypeList.value.size) { itemIndex ->
                        val accomodationType = accomodationTypeList.value[itemIndex]
                        AccomodationCard(
                            accomodationType = accomodationType,
                            onItemClick = { accomodationType ->
                                showDetails(accomodationType)
                            }
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.startFetchAccommodationList()
    }
}

@Composable
private fun HeaderSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_welcome),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun HeaderSectionPreview() {
    HeaderSection(modifier = Modifier.background(color = Color.White))
}