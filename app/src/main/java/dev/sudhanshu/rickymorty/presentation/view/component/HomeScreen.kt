package dev.sudhanshu.rickymorty.presentation.view.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.sudhanshu.rickymorty.presentation.viewmodel.CharacterViewModel
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.sudhanshu.rickymorty.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen() {
    val viewModel: CharacterViewModel = hiltViewModel()
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val listState = rememberLazyGridState()
    val context = LocalContext.current

    if (characters.isEmpty() && error != null) {
        // Showing an error message with an image
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.error),
                    contentDescription = "Error",
                    Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Something went wrong!")
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(characters) { character ->
                    CharacterCard(character = character)
                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            // Collecting scroll state to trigger loading more data
            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo }
                    .map { it.visibleItemsInfo.lastOrNull() }
                    .distinctUntilChanged()
                    .filter { it != null && it.index >= characters.size - 1 }
                    .collect {
                        viewModel.fetchCharacters()
                    }
            }
        }
    }

    // Showing error in toast if there's already data
    LaunchedEffect(error) {
        error?.let {
            if (characters.isNotEmpty()) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


