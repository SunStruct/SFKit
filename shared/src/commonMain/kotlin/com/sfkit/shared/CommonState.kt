package com.sfkit.shared

import com.sfkit.shared.structs.Character

data class CommonState(
    val characters: Map<String, Character> = emptyMap(),
    val currentCharacter: CurrentCharacter = CurrentCharacter.NoSelection
)

sealed class CurrentCharacter {
    object NoSelection : CurrentCharacter()
    data class SelectedCharacter(val id: String) : CurrentCharacter()
    data class NewCharacterCreation(val id: String, val state: CharacterCreationState) :
        CurrentCharacter()
}

enum class CharacterCreationState {
    NAME//, RACE
}