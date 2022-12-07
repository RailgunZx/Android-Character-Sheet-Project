package com.example.charactersheetempty;

// NOTE: For some reason, this Java implementation does not work in Kotlin despite not giving any syntax errors.
// I believe I narrowed it down to an issue regarding overriding the constructor of an abstract class, but I'm not entirely sure.
// An alternative version of this code in a file called ItemComponentKotlin.kt was created as a substitute which will be used for the project instead.
// The design of the Kotlin alternative is slightly altered as a result of this which will affect how the code is utilized.
// This java file will remain for reference purposes only.

public abstract class ItemComponent {

    public ItemComponent(){

    }

    public abstract void ApplyEffect(Character c);
}

class AddStatsJava extends ItemComponent{
    public int addMaxHealth = 0;
    public int addSpellSlots = 0;
    public int addConstitution = 0;
    public int addStrength = 0;
    public int addArcana = 0;


    public AddStatsJava(int hp, int spell, int con, int str, int arc) {
        super();
        addMaxHealth = hp;
        addSpellSlots = spell;
        addConstitution = con;
        addStrength = str;
        addArcana = arc;
    }


    @Override
    public void ApplyEffect(Character c) {
        c.maxHealthActual += addMaxHealth;
        c.maxSpellSlotsActual += addSpellSlots;
        c.constitutionActual += addConstitution;
        c.strengthActual += addStrength;
        c.arcanaActual += addArcana;
    }

}

class AddWeightJava extends ItemComponent{
    float addWeight = 0f;

    public AddWeightJava(float w){
        super();
        addWeight = w;
    }

    @Override
    public void ApplyEffect(Character c) {
        c.equipLoad += addWeight;

    }

}
