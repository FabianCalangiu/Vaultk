package com.unibo.android.uicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.uicompose.screens.home.HomeScreen
import com.unibo.android.uicompose.screens.home.HomeViewModelFactory
import com.unibo.android.uicompose.ui.theme.CorsoLp2526Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CorsoLp2526Theme {
                CorsoLp2526App()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun CorsoLp2526App() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when (currentDestination) {
                AppDestinations.HOME -> {
                    HomeContent(modifier = Modifier.padding(innerPadding))
                }
                AppDestinations.FAVORITES -> {
                    Greeting(
                        name = "Favorite",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                AppDestinations.PROFILE -> {
                    Greeting(
                        name = "Profile",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

//            Greeting(
//                name = "Android",
//                modifier = Modifier.padding(innerPadding)
//            )
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
}


@Composable
private fun HomeContent(modifier: Modifier = Modifier) {
    HomeScreen(
        viewModel = viewModel(
            factory = HomeViewModelFactory(
                startFetchAccommodationListUseCase = UseCasesProvider.startFetchAccommodationListUseCase,
                fetchAccommodationListUpdatesUseCase = UseCasesProvider.fetchAccommodationListUpdatesUseCase
            )
        ),
        showDetails = { accomodationType ->
//            val accomodationTypeJsonString = Json.encodeToString(accomodationType)
//            navController.navigate(
//                DetailsScreen(accomodationTypeJsonString)
//            )
        },
        modifier = modifier
    )
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CorsoLp2526Theme {
        Greeting("Android")
    }
}