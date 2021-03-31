package com.gonhog.theancientworld.items;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class CookedCustomMeat extends Item {
    public CookedCustomMeat() {
        super(new Item.Properties()
                .group(TheAncientWorld.TAB)
                .food(new Food.Builder()
                        .hunger(8)
                        .meat()
                        .saturation(8)
                        .build())
        );
    }
}
