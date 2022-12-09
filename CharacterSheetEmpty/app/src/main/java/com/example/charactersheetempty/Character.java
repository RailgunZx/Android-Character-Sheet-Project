package com.example.charactersheetempty;
import java.util.ArrayList;
import java.util.List;

public class Character {
    public String playerName;
    public String playerRace;
    public String playerClass;

    public int playerLevel;
    public int currentHealth;

    public int maxSpellSlotsBase;
    public int currentSpellSlots;
    public int constitutionBase;
    public int strengthBase;
    public int arcanaBase;

    public int maxHealthActual;
    public int maxSpellSlotsActual;
    public int constitutionActual;
    public int strengthActual;
    public int arcanaActual;

    public float equipLoad;

    private List<Item> items = new ArrayList<Item>();


    public Character(String name, String race, String _class, int level){
        playerName = name;
        playerLevel = level;
        playerRace = race;
        playerClass = _class;
        LoadCharacterStats();
        currentHealth = maxHealthActual;
        currentSpellSlots = maxSpellSlotsActual;
    }

    public void LoadCharacterStats(){
        constitutionBase = 10 + playerLevel * 3;
        maxSpellSlotsBase = 4 + playerLevel;
        strengthBase = 5 + playerLevel * 2;
        arcanaBase = 10 + playerLevel * 5;
        equipLoad = 0f;

        constitutionActual = constitutionBase;
        maxSpellSlotsActual = maxSpellSlotsBase;
        strengthActual = strengthBase;
        arcanaActual = arcanaBase;
        maxHealthActual = constitutionActual + 10;


        // Load actual from items
        for (Item item : items) {
            item.ApplyComponents(this);
        }
    }

    public void LevelUp(int l){
        playerLevel++;
        LoadCharacterStats();
    }

    public boolean EquipItem(int index){
        if (items.get(index) != null){
            items.get(index).isEquipped = true;
            LoadCharacterStats();
            return true;
        }
        return false;
    }

    public void AddItem(Item i){
        items.add(i);
        LoadCharacterStats();
    }

    public boolean RemoveItem(Item i){
        for (Item item : items) {
            if (item.itemID == i.itemID){
                items.remove(item);
                LoadCharacterStats();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String info = new String();
        info += "Character: " + playerName + "\n";
        info += "Level: " + playerLevel + "\n";
        info += "Health: " + currentHealth + "\\" + maxHealthActual + "\n";
        info += "Spell Slots: " + currentSpellSlots + "\\" + maxSpellSlotsActual + "\n";
        info += "Constitution: " + constitutionActual + "\n";
        info += "Strength: " + strengthActual + "\n";
        info += "Arcana: " + arcanaActual + "\n";
        info += "Equip Load: " + equipLoad + "\n\n";
        info += "Items in inventory:" + "\n";

        for (Item item : items) {
            info += item.itemName + ", equipped: " + item.isEquipped + "\n";
        }

        return info;
    }
}