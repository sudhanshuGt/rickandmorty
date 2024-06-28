package dev.sudhanshu.rickymorty.presentation.view.component

import androidx.compose.ui.draw.clip


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import coil.compose.rememberImagePainter
import dev.sudhanshu.rickymorty.domain.Character

@Composable
fun CharacterCard(character: Character) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Image(
                painter = rememberImagePainter(character.image),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = character.name, modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = character.status,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                color = if (character.status == "Alive") Color.Green else Color.Red
            )
        }
    }
}
