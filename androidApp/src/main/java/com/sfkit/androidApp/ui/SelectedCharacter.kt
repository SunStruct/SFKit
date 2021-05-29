package com.sfkit.androidApp.ui

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sfkit.androidApp.R
import com.sfkit.androidApp.ui.theme.SFKitTheme
import com.sfkit.shared.CommonState
import com.sfkit.shared.CurrentCharacter
import com.sfkit.shared.structs.Character

@Composable
fun SelectedCharacter(
    state: CommonState,
    currentCharacter: CurrentCharacter.SelectedCharacter,
    onNavigationIconClickListener: () -> Unit
) {
    val character = state.characters[currentCharacter.id]

    Scaffold(
        topBar = {
            val characterName = character?.name ?: stringResource(R.string.unknown_character)
            TopAppBar(
                title = { Text(characterName) },
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClickListener) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = character?.prettifyCharacter()
                    ?: stringResource(R.string.error_unknown_character, currentCharacter.id),
                modifier = Modifier.wrapContentSize(Alignment.TopStart),
                color = if (character == null) MaterialTheme.colors.error else Color.Unspecified
            )
        }
    }
}

@Composable
private fun Character.prettifyCharacter(): String {
    val iter = toString().iterator()
    var margin = ""
    val result = StringBuilder()
    var char: Char
    var lastCharIsComma = false
    while (iter.hasNext()) {
        char = iter.nextChar()
        when (char) {
            '(' -> {
                result.append("(\n")
                margin += "\t"
                result.append(margin)
            }
            ')' -> {
                result.append("\n")
                result.append(margin)
                result.append(char)
                margin = margin.dropLast(1)
            }
            ',' -> {
                lastCharIsComma = true
                continue
            }
            ' ' -> {
                if (lastCharIsComma) {
                    result.append("\n")
                    result.append(margin)
                } else {
                    result.append(char)
                }
            }
            else -> result.append(char)
        }
        lastCharIsComma = false
    }
    return result.toString()
}

@ExperimentalAnimationApi
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun SelectedCharacterPreview() {
    val id = "0"
    val currentCharacter = CurrentCharacter.SelectedCharacter("2")
    SFKitTheme {
        SelectedCharacter(
            state = CommonState(
                characters = mapOf(id to Character(id)),
                currentCharacter = currentCharacter
            ),
            currentCharacter = currentCharacter,
            onNavigationIconClickListener = {}
        )
    }
}