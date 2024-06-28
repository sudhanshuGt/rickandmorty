package dev.sudhanshu.rickymorty.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.sudhanshu.rickymorty.ui.theme.RickyMortyTheme
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import dev.sudhanshu.rickymorty.presentation.view.navigation.BottomNavigationBar
import dev.sudhanshu.rickymorty.presentation.view.navigation.NavigationHost
import dev.sudhanshu.rickymorty.ui.theme.RickyMortyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyMortyTheme {
                RickyMortyApp()
            }
        }
    }

    @Composable
    fun RickyMortyApp() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationHost(navController = navController)
            }
        }
    }
}




