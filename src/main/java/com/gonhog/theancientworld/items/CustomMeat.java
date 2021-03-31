package com.gonhog.theancientworld.items;

import com.gonhog.theancientworld.TheAncientWorld;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CustomMeat extends Item {
    public CustomMeat() {
        super(new Item.Properties()
                .group(TheAncientWorld.TAB)
                .food(new Food.Builder()
                        .hunger(4)
                        .meat()
                        .saturation(4)
                        .build())
        );
    }
}
