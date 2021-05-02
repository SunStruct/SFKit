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
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import com.sfkit.androidApp.ui.theme.SFKitTheme
import com.sfkit.shared.Greeting

fun greet(): String = Greeting().greeting()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SFKitTheme {
                Scaffold {
                    Content()
                }
            }
        }
    }
}

@Composable
private fun Content() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = greet(),
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun ContentPreview() {
    SFKitTheme {
        Content()
    }
}