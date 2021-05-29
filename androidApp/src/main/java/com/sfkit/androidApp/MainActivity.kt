/*
 * SFKit - character generator for TTRPG Starfinder
 * Copyright (C) 2021 geomatix and Anton Kovalyov <keldzh@gmail.com>
 *
 * This file is part of SFKit.
 *
 * SFKit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SFKit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sfkit.androidApp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.sfkit.androidApp.ui.CharactersList
import com.sfkit.androidApp.ui.SelectedCharacter
import com.sfkit.androidApp.ui.theme.SFKitTheme
import com.sfkit.shared.*
import com.sfkit.shared.structs.Character
import org.reduxkotlin.StoreSubscription

class MainActivity : ComponentActivity() {
    // TODO replace with DI
    private val entryPoint = EntryPoint()
    private lateinit var unsubscribe: StoreSubscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var state by mutableStateOf(entryPoint.state)
            unsubscribe = entryPoint.store.subscribe {
                val newState = entryPoint.store.state
                state = newState
                Log.v(TAG, newState.toString())
            }
            MainLayout(
                state,
                createNewCharacter = { entryPoint.store.dispatch(CreateNewCharacter()) },
                characterSelected = { id -> entryPoint.store.dispatch(SelectCharacter(id)) },
                backToListNavigation = ::backToListNavigation
            )
        }

        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroy() {
        if (::unsubscribe.isInitialized) {
            unsubscribe()
        }
        super.onDestroy()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when (entryPoint.state.currentCharacter) {
                is CurrentCharacter.NoSelection -> finish()
                is CurrentCharacter.SelectedCharacter -> backToListNavigation()
                else -> {}
            }
        }
    }

    private fun backToListNavigation() {
        entryPoint.store.dispatch(SelectCharacter(null))
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

// TODO add character removing ?
// TODO start implementing character creation
@Composable
private fun MainLayout(
    state: CommonState,
    createNewCharacter: () -> Unit,
    characterSelected: (id: String) -> Unit,
    backToListNavigation: () -> Unit
) {
    SFKitTheme {
        // TODO replace with explode animation
        Crossfade(targetState = state.currentCharacter) { currentCharacter ->
            when {
                currentCharacter === CurrentCharacter.NoSelection ->
                    CharactersList(state, createNewCharacter, characterSelected)
                currentCharacter is CurrentCharacter.SelectedCharacter ->
                    SelectedCharacter(state, currentCharacter, backToListNavigation)
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun MainLayoutPreview() {
    MainLayout(
        state = CommonState(characters = mapOf("0" to Character("0"))),
        createNewCharacter = {},
        characterSelected = {},
        backToListNavigation = {}
    )
}