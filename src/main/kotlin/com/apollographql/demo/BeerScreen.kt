@file:OptIn(ExperimentalMaterial3Api::class, ApolloExperimental::class)

package com.apollographql.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.compose.toState
import com.apollographql.demo.theme.Purple80
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun ContentWrapper(content: @Composable ColumnScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            content()
        }
        val navigator = LocalNavigator.currentOrThrow
        IconButton(
            onClick = { navigator.pop() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.ShoppingCart, null)
            Text("  Add to Cart")
        }
    }
}

class BeerScreen(val id: String, val unused: String) : Screen {
    @Composable
    override fun Content() {
        return BeerScreen(id)
    }
}




































@Composable
fun BeerScreen(upc: String) {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}






















