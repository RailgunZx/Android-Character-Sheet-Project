package com.example.charactersheetempty

abstract class ItemComponentKotlin {
    abstract fun ApplyEffect(c : Character)
}

class AddStats(hp : Int, spell : Int, con : Int, str : Int, arc : Int): ItemComponentKotlin() {
    var addMaxHealth = 0
    var addSpellSlots = 0
    var addConstitution = 0
    var addStrength = 0
    var addArcana = 0

    init {
        addMaxHealth = hp
        addSpellSlots = spell
        addConstitution = con
        addStrength = str
        addArcana = arc
    }


    override fun ApplyEffect(c: Character) {
        c.maxHealthActual += addMaxHealth
        c.maxSpellSlotsActual += addSpellSlots
        c.constitutionActual += addConstitution
        c.strengthActual += addStrength
        c.arcanaActual += addArcana
    }
}

class AddWeight(w:Float) : ItemComponentKotlin(){
    var addWeight = 0f
    init {
        addWeight = w
    }

    override fun ApplyEffect(c: Character){
        c.equipLoad += addWeight
    }
}