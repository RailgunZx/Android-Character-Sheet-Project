package com.example.charactersheetempty;
import java.util.ArrayList;
import java.util.List;

public class Item {
    public int itemID;
    public String itemName;
    public String itemDescription;
    public boolean isEquipped = false;

    private List<ItemComponentKotlin> passiveComponents = new ArrayList<ItemComponentKotlin>();
    private List<ItemComponentKotlin> equippedComponents = new ArrayList<ItemComponentKotlin>();

    public Item(int id, String name, String description){
        itemID = id;
        itemName = name;
        itemDescription = description;
        isEquipped = false;
    }

    public Item(int id, String name, String description, boolean equipped){
        itemID = id;
        itemName = name;
        itemDescription = description;
        isEquipped = equipped;
    }

    public void AddPassiveComponent(ItemComponentKotlin c){
        passiveComponents.add(c);
    }

    public void AddEquippedComponent(ItemComponentKotlin c){
        equippedComponents.add(c);
    }

    public boolean RemovePassiveComponent(Class<?> c){
        for (ItemComponentKotlin p : passiveComponents) {
            if (p.getClass().equals(c)){
                passiveComponents.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean RemoveEquippedComponent(Class<?> c){
        for (ItemComponentKotlin e : equippedComponents) {
            if (e.getClass().equals(c)){
                passiveComponents.remove(e);
                return true;
            }
        }
        return false;
    }

    public void ApplyComponents(Character c){
        for (ItemComponentKotlin p : passiveComponents) {
            p.ApplyEffect(c);
        }

        if (!isEquipped)
            return;

        for (ItemComponentKotlin e : equippedComponents) {
            e.ApplyEffect(c);
        }
    }
}
