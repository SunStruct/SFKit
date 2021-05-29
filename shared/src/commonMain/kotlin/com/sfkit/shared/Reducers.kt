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

package com.sfkit.shared

import com.sfkit.shared.structs.Character
import com.sfkit.shared.utils.randomUUID
import org.reduxkotlin.Reducer

val commonReducer: Reducer<CommonState> = { state, action ->
    when (action) {
        is CreateNewCharacter -> {
            var id: String
            do {
                id = randomUUID()
            } while (state.characters.containsKey(id))

            val character = if (action.name.isNotBlank()) {
                Character(id, action.name)
            } else {
                Character(id)
            }
            state.copy(characters = state.characters.plus(id to character))
        }
        is SelectCharacter -> {
            if (action.id != null && state.characters.containsKey(action.id)) {
                state.copy(currentCharacter = CurrentCharacter.SelectedCharacter(action.id))
            } else {
                state.copy(currentCharacter = CurrentCharacter.NoSelection)
            }
        }
        else -> state
    }
}