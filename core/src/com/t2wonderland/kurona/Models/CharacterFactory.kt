package com.t2wonderland.kurona.Models

import com.t2wonderland.kurona.Interfaces.ICharacterObject
import com.t2wonderland.kurona.Objects.Kurona

internal class CharacterFactory {
    fun createCharacter(): ICharacterObject {
        val character = Kurona()
        character.setInitialPosition(1f, 1f)

        return character
    }
}
