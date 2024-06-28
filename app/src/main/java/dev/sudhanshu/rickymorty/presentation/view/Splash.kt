package dev.sudhanshu.rickymorty.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.sudhanshu.rickymorty.presentation.view.component.SplashScreen
import dev.sudhanshu.rickymorty.ui.theme.RickyMortyTheme

@AndroidEntryPoint
class Splash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickyMortyTheme {
               SplashScreen {
                   navigateToMain()
               }
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    RickyMortyTheme {
       SplashScreen {

       }
    }
}