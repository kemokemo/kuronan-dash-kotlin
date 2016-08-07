package com.t2wonderland.kurona.Models;

import com.t2wonderland.kurona.Interfaces.ICharacterObject;
import com.t2wonderland.kurona.Objects.Kurona;

class CharacterFactory {
    ICharacterObject createCharacter() {
        ICharacterObject character =  new Kurona();
        character.setInitialPosition(1,1);

        return character;
    }
}
