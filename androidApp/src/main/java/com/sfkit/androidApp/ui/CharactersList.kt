package com.sfkit.androidApp.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sfkit.androidApp.R
import com.sfkit.androidApp.ui.theme.SFKitTheme
import com.sfkit.shared.CommonState
import com.sfkit.shared.structs.Character

@Composable
fun CharactersList(
    state: CommonState,
    createNewCharacter: () -> Unit,
    characterSelected: (id: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = createNewCharacter) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
    ) {
        LazyColumn {
            items(state.characters.values.toList()) { character ->
                CharacterListItem(character, characterSelected)
            }
        }
    }
}

@Composable
private fun CharacterListItem(character: Character, characterSelected: (id: String) -> Unit) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable { characterSelected(character.id) }
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = character.name ?: stringResource(R.string.unknown_character),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun CharacterListItemPreview() {
    SFKitTheme {
        CharacterListItem(character = Character("0")) {}
    }
}