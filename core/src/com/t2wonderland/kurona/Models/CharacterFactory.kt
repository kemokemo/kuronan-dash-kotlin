package com.t2wonderland.kurona.Models

import com.t2wonderland.kurona.Interfaces.ICharacterObject
import com.t2wonderland.kurona.Objects.Kurona

internal class CharacterFactory(val selectedCharacter: CharacterSelect) {
    fun createCharacter(): ICharacterObject {
        if(selectedCharacter == CharacterSelect.Kurona){
            var character = Kurona()
            character.setInitialPosition(1f, 1f)
            return character
        }
            // TODO: 設定に応じて実体を作る
        else return Kurona()
    }
}
