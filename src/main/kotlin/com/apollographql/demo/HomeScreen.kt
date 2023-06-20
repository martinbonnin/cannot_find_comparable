package com.apollographql.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.compose.toState
import com.apollographql.demo.api.GetBeersQuery

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        return HomeContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ApolloExperimental::class)
@Composable
fun HomeContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("All Beers", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            val apolloClient = MainApplication.apolloClient

            val state = apolloClient.query(GetBeersQuery()).toState()
            val navigator = LocalNavigator.currentOrThrow

            val response = state.value
            when {
                response == null -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                response.exception != null -> {
                    response.exception?.printStackTrace()
                    Text(
                        "Oops something wrong happened",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    BeersGrid(
                        modifier = Modifier.fillMaxSize(),
                        beers = response.data!!.beers,
                        onClicked = {
                            navigator.push(BeerScreen(it.upc, ""))
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun BeersGrid(
    modifier: Modifier,
    beers: List<GetBeersQuery.Beer>,
    onClicked: (GetBeersQuery.Beer) -> Unit
) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
        items(beers) { beer ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        onClicked(beer)
                    }
                    .padding(8.dp)
            ) {
                BeerItem(beer.name, beer.picture)
            }
        }
    }
}

@Composable
fun BeerItem(name: String, picture: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = picture,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(
                    Brush.verticalGradient(
                        0f to Color.Black.copy(alpha = 0f),
                        1f to Color.DarkGray.copy(alpha = 0.8f)
                    )
                )
        ) {
            Text(
                text = name,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .padding(top = 32.dp),
                color = Color.White,
            )
        }
    }
}