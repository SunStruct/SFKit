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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sfkit.androidApp.databinding.ActivityMainBinding
import com.sfkit.shared.Greeting

fun greet(): String = Greeting().greeting()

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = greet()
    }
}
