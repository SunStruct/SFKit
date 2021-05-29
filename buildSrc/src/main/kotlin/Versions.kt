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

object Versions {
    // Update to 1.5.0 breaks compilation because current version of JetPack Compose compiler plugin
    // doesn't support Kotlin 1.5.0
    const val kotlin = "1.4.32"
    const val reduxKotlin = "0.5.5"

    // Android settings
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    // Android dependencies
    const val activity = "1.3.0-alpha08"
    const val compose = "1.0.0-beta07"
    const val core = "1.6.0-beta01"
    const val material = "1.3.0"

    // Android test dependencies
    const val junit = "4.13"
}